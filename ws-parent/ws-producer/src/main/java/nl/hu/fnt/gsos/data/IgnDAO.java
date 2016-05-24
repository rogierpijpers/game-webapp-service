package nl.hu.fnt.gsos.data;

import java.io.IOException;

import nl.hu.fnt.gsos.util.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;


public class IgnDAO {
	private static final String API_BASE = "https://videogamesrating.p.mashape.com/get.php";
	private static final String API_KEY = "rhwpsesgjwmshTs0P9FWTaCpLiKYp1pudpVjsnxYVxmJ4PjoJZ";
	
	
	public static double getRating(String gameTitle){		
		JSONArray results = null;
		
		gameTitle = gameTitle.replaceAll(" ", "+");		
		
		try {
			results = getResults(gameTitle);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		double score = 0.0;
		if(results != null){
			
			try {
				String scoreStr = results.getJSONObject(0).getString("score");
				if(!scoreStr.isEmpty()){
					score = Double.parseDouble(scoreStr);
				}
			} catch (JSONException e) {
				//do nothing
			}
			return score;
		}else{
			return score;
		}
	}
	
	private static JSONArray getResults(String gameTitle) throws IOException, JSONException{
		JSONParser parser = new JSONParser(API_BASE);
		
		parser.addParam("count", "1");
		parser.addParam("game", gameTitle);
		
		parser.addHeader("X-Mashape-key", API_KEY);
		parser.addHeader("Accept", "application/json");
		
		return parser.getObjects();
	}
	
	
}

