package service;

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
import dao.RoomDAO;

public class ChessService {
	private final PieceDAO pieceDAO = new PieceDAO();
	private final RoomDAO roomDAO = new RoomDAO();
	private final GameStateDAO gameStateDAO = new GameStateDAO();
	private final Map<Long, GameManager> session = new HashMap<>();

	public Object findWinner(Map<String, Object> model, long roomID) throws SQLException {
		GameManager gameManager = createGameManager(roomID);
		try {
			model.put("winner", gameManager.findWinner());
		} catch (IllegalArgumentException | NullPointerException e) {
			model.put("error", e.getMessage());
		}
		return model;
	}

	public List<GameStatistic> createStatistics(long roomID) throws SQLException {
		return createGameManager(roomID).createStatistics();
	}

	private GameManager createGameManager(Long roomID) throws SQLException {
		return session.getOrDefault(roomID,
			new GameManager(pieceDAO.findAll(roomID), gameStateDAO.findGameState(roomID)));
	}

	public GameManager resume(long roomID) throws SQLException {
		return createGameManager(roomID);
	}

	public GameManager start(Long roomID) throws SQLException {
		GameManager gameManager = new GameManager(new PieceFactory().createPieces(), GameState.RUNNING_WHITE_TURN);
		Map<Location, Piece> board = gameManager.getBoard();

		for (Location location : board.keySet()) {
			pieceDAO.addPiece(roomID, location, board.get(location));
		}
		gameStateDAO.addGameState(roomID, GameState.RUNNING_WHITE_TURN);

		session.put(roomID, gameManager);
		return gameManager;
	}

	public Object move(Long roomID, String now, String destination) throws SQLException {
		GameManager gameManager = createGameManager(roomID);

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

	public Map<String, Object> findRoom() throws SQLException {
		return roomDAO.findAll();
	}

	public String createRoom() throws SQLException {
		return roomDAO.createRoom();
	}

	public boolean deleteRoom(String roomID) throws SQLException {
		roomDAO.delete(roomID);
		return true;
	}
}
