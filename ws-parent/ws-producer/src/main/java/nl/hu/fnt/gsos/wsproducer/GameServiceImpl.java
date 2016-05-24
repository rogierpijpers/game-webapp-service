package nl.hu.fnt.gsos.wsproducer;

import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.beanutils.BeanUtils;

import nl.hu.fnt.gsos.data.Game;
import nl.hu.fnt.gsos.data.GameFacade;
import nl.hu.fnt.gsos.wsinterface.Fault;
import nl.hu.fnt.gsos.wsinterface.Fault_Exception;
import nl.hu.fnt.gsos.wsinterface.GameDetailsRequest;
import nl.hu.fnt.gsos.wsinterface.GameDetailsResponse;
import nl.hu.fnt.gsos.wsinterface.GameListResponse;
import nl.hu.fnt.gsos.wsinterface.GamesResponse;
import nl.hu.fnt.gsos.wsinterface.ObjectFactory;
import nl.hu.fnt.gsos.wsinterface.Void;
import nl.hu.fnt.gsos.wsinterface.WSInterface;

@WebService(endpointInterface = "nl.hu.fnt.gsos.wsinterface.WSInterface")
public class GameServiceImpl implements WSInterface {

	@Override
	public GameListResponse getGames(Void gamesRequest) throws Fault_Exception {
		ObjectFactory factory = new ObjectFactory();
		GameListResponse response = null;
		try {
			response = factory.createGameListResponse();
			List<Game> games = GameFacade.getGames();
			for (Game game : games) {
				GamesResponse resp = factory.createGamesResponse();
				resp.setAppId(game.getAppId());
				resp.setTitle(game.getTitle());
				response.getGamesResponse().add(resp);
			}
		} catch (RuntimeException e) {
			Fault x = factory.createFault();
			x.setErrorCode((short) 1);
			x.setMessage(e.getMessage());
			Fault fault = new Fault();
			fault.setMessage(e.getMessage());
			throw new Fault_Exception(e.toString(), x);
		}
		return response;
	}

	@Override
	public GameDetailsResponse getGameDetails(
			GameDetailsRequest gameDetailsRequest) throws Fault_Exception {
		ObjectFactory factory = new ObjectFactory();
		GameDetailsResponse response = null;
		try {
			response = factory.createGameDetailsResponse();
			Game game = GameFacade
					.getGameDetails(gameDetailsRequest.getAppId());
			BeanUtils.copyProperties(response, game);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeException e) {
			Fault x = factory.createFault();
			x.setErrorCode((short) 1);
			x.setMessage(e.getMessage());
			Fault fault = new Fault();
			fault.setMessage(e.getMessage());
			throw new Fault_Exception(e.toString(), x);
		}
		return response;
	}
	/*
	 * 
	 * 
	 * 
	 * /*
	 * 
	 * @Override public GamesResponse getGames(Request request) throws
	 * Fault_Exception{ ObjectFactory factory = new ObjectFactory(); Response
	 * response = factory.createResponse(); try { double result =
	 * Math.pow(request.getX().doubleValue(), request
	 * .getPower().doubleValue()); // x en power zijn altijd gehele getallen dan
	 * is er geen afronding BigInteger actualResult = BigInteger.valueOf((long)
	 * result); response.setResult(actualResult); } catch (RuntimeException e) {
	 * Fault x = factory.createFault(); x.setErrorCode((short) 1);
	 * x.setMessage("Kan de macht van " + request.getX() + " tot de macht " +
	 * request.getPower().toString() + " niet berekenen."); Fault fault = new
	 * Fault(); fault.setMessage("Er ging iets fout..."); throw new
	 * Fault_Exception("Er ging iets fout", fault); } return response; }
	 * 
	 * @Override public GameDetailsResponse getGameDetails( GameDetailsRequest
	 * gameDetailsRequest) { // TODO Auto-generated method stub return null; }
	 */
}
