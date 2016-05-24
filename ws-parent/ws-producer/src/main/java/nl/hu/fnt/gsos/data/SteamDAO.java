package nl.hu.fnt.gsos.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.hu.fnt.gsos.util.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SteamDAO {
	private static final String API_BASE = "http://store.steampowered.com/api/";

	public static List<Game> getGames() {
		nl.hu.fnt.gsos.util.JSONParser parser = new JSONParser("http://api.steampowered.com/ISteamApps/GetAppList/v0002/");
		parser.printURLonExecute(true);

		JSONArray result;
		List<Game> allGames = new ArrayList<Game>();

		Set<String> filterWords = new HashSet<String>();
		filterWords.add("Demo"); filterWords.add("Film");
		filterWords.add("Trailer"); filterWords.add("SDK");
		filterWords.add("Pack"); filterWords.add("Expansion");
		filterWords.add("Commercial"); filterWords.add("Pre-order");
		filterWords.add("Beta"); filterWords.add("Key");
		filterWords.add("Collector"); filterWords.add("Content");
		filterWords.add("Dedicated Server"); filterWords.add("Soundtrack");
		filterWords.add("Sound Track"); filterWords.add("Art Book");
		filterWords.add("Extras"); filterWords.add("Tool"); 
		filterWords.add("Season Pass"); filterWords.add("Skin"); 
		filterWords.add("Bonus"); filterWords.add("Viewer"); 
		filterWords.add("DLC"); filterWords.add("Video");
		filterWords.add("Announcement"); filterWords.add("Announcer");
		filterWords.add("Tutorial"); filterWords.add("Help");
		filterWords.add("Bundle"); filterWords.add("Wallpaper");
		filterWords.add("Additional"); filterWords.add("Edition");
		filterWords.add("Activation"); filterWords.add("Costume");
		filterWords.add("Sculpting"); filterWords.add("Concepting");
		filterWords.add("3DS"); filterWords.add("Addon");
		filterWords.add("Add-on"); filterWords.add("Robotpencil");
		filterWords.add("Commentary"); filterWords.add("Upgrade");

		int appId = 0;
		String name = "";

		try {
			JSONArray returnValue = parser.getObjects();

			result = returnValue.getJSONObject(0).getJSONObject("applist").getJSONArray("apps");
				// LIMIT 100
			for (int i = 0; i < 100; i++) {
				appId = result.getJSONObject(i).getInt("appid");
				name = result.getJSONObject(i).getString("name");

				if (!containsWord(filterWords, name)) {
					allGames.add(new Game(appId, name));
				}
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allGames;
	}

	public static Game getGameDetails(int appId) {
		JSONParser parser = new JSONParser(API_BASE + "appdetails?");
		parser.addParam("appids", String.valueOf(appId));

		parser.printURLonExecute(true);

		JSONArray result = null;
		String name = "";
		Game game = new Game(appId, "");

		try {
			result = parser.getObjects();
			JSONObject jsonObj = result.getJSONObject(0);
			name = jsonObj.getJSONObject(String.valueOf(appId)).getJSONObject("data").getString("name");
			game = new Game(appId, name);
			game.setDescription(jsonObj.getJSONObject(String.valueOf(appId)).getJSONObject("data")
					.getString("detailed_description"));
			game.setHeaderImage(jsonObj.getJSONObject(String.valueOf(appId)).getJSONObject("data").getString("header_image"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return game;
	}

	private static boolean containsWord(Set<String> words, String appName) {
		for (String word : words) {
			if (appName.contains(word)) {
				return true;
			}
		}
		return false;
	}
}