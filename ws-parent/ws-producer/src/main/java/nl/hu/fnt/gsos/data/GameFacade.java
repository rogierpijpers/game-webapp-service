package nl.hu.fnt.gsos.data;

import java.util.List;

/**
 * GameFactory in klassendiagram maar deze naam leek me beter
 * @author Rogier
 *
 */
public class GameFacade {

	/**
	 * Al klaar
	 * @return
	 */
	public static List<Game> getGames(){
		return SteamDAO.getGames();
	}
	
	
	
	/**
	 * Hier in wordt dus alles samengevoegd:
	 * -gameDetails van SteamDAO
	 * -review van GiantBomb
	 * -rating van IGN
	 * -videoId van youtubeDAO
	 * 
	 * @param appId
	 * @return
	 */
	public static Game getGameDetails(int appId){
            Game game = SteamDAO.getGameDetails(appId);
            String gameTitle = game.getTitle();
            game.setGiantBombReview(GiantBombDAO.getReview(gameTitle));
            game.setIgnRating(IgnDAO.getRating(gameTitle));
            game.setVideoId(YoutubeDAO.getVideoId(gameTitle));
		return game;
	}
	
}
