import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.GameManager;
import chess.board.Location;
import chess.gamestate.GameState;
import chess.piece.Piece;
import chess.piece.PieceFactory;
import chess.result.GameStatistic;
import dao.GameStateDAO;
import dao.PieceDAO;
import spark.Request;

public class WebController {
	private final PieceDAO pieceDAO = new PieceDAO();
	private final GameStateDAO gameStateDAO = new GameStateDAO();

	public Object winner(GameManager gameManager) {
		Map<String, Object> model = new HashMap<>();
		try {
			model.put("winner", gameManager.findWinner());
		} catch (IllegalArgumentException | NullPointerException e) {
			model.put("error", e.getMessage());
		}
		return model;
	}

	public List<GameStatistic> status(GameManager gameManager) {
		return gameManager.createStatistics();
	}

	public GameManager resume(Long roomID) throws SQLException {
		Map<Location, Piece> allPieces = pieceDAO.findAll(roomID);
		GameState gameState = gameStateDAO.findGameState(roomID);

		return new GameManager(allPieces, gameState);
	}

	// todo : 여기서 ID가 생겨야하지않을까?
	public GameManager start(Long roomID) throws SQLException {

		gameStateDAO.deleteAll(roomID);
		pieceDAO.deleteAll(roomID);

		GameManager gameManager = new GameManager(new PieceFactory().createPieces(), GameState.RUNNING_WHITE_TURN);

		Map<Location, Piece> board = gameManager.getBoard();
		for (Location location : board.keySet()) {
			pieceDAO.addPiece(roomID, location, board.get(location));
		}
		gameStateDAO.addGameState(roomID, GameState.RUNNING_WHITE_TURN);
		return gameManager;
	}

	public Object move(Long roomID, Request request) throws SQLException {
		String now = request.queryParams("now");
		String destination = request.queryParams("destination");
		GameManager gameManager = new GameManager(pieceDAO.findAll(roomID), gameStateDAO.findGameState(roomID));
		try {
			gameManager.movePiece(Location.of(now), Location.of(destination));
		} catch (IllegalArgumentException | NullPointerException e) {
			Map<String, String> message = new HashMap<>();
			message.put("error", e.getMessage());
			return message;
		}

		pieceDAO.deletePiece(roomID, Location.of(destination));
		pieceDAO.updateLocation(roomID, Location.of(now), Location.of(destination));

		GameState gameState = gameStateDAO.findGameState(roomID);
		gameStateDAO.updateMessage(roomID, gameState, gameState.findOpposingTeam());

		return gameManager;
	}
}
