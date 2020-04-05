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

	public GameManager resume() throws SQLException {
		Map<Location, Piece> allPieces = new PieceDAO().findAll();
		GameState gameState = new GameStateDAO().findGameState();

		return new GameManager(allPieces, gameState);
	}

	public GameManager start() throws SQLException {
		GameStateDAO gameStateDAO = new GameStateDAO();
		gameStateDAO.deleteAll();
		new PieceDAO().deleteAll();

		GameManager gameManager = new GameManager(new PieceFactory().createPieces(), GameState.RUNNING_WHITE_TURN);
		PieceDAO pieceDAO = new PieceDAO();

		Map<Location, Piece> board = gameManager.getBoard();
		for (Location location : board.keySet()) {
			pieceDAO.addPiece(location, board.get(location));
		}
		gameStateDAO.addGameState(GameState.RUNNING_WHITE_TURN);
		return gameManager;
	}

	public Object move(Request request, GameManager gameManager) throws SQLException {
		String now = request.queryParams("now");
		String destination = request.queryParams("destination");

		try {
			gameManager.movePiece(Location.of(now), Location.of(destination));
		} catch (IllegalArgumentException | NullPointerException e) {
			Map<String, String> message = new HashMap<>();
			message.put("error", e.getMessage());
			return message;
		}

		PieceDAO pieceDAO = new PieceDAO();
		pieceDAO.deletePiece(Location.of(destination));
		pieceDAO.updateLocation(Location.of(now), Location.of(destination));
		return gameManager;
	}
}
