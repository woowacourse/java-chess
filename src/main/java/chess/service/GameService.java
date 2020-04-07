package chess.service;

import java.sql.SQLException;

import chess.dao.GameDAO;
import chess.dao.RoomDAO;
import chess.domain.Color;
import chess.domain.GameManager;
import chess.domain.PieceScore;
import chess.domain.board.Position;
import chess.domain.piece.Pieces;
import chess.dto.PiecesResponseDTO;

public class GameService {
	private static final GameService GAME_SERVICE = new GameService();

	GameDAO gameDAO = GameDAO.getInstance();
	RoomDAO roomDAO = RoomDAO.getInstance();

	private GameService() {
	}

	public static GameService getInstance() {
		return GAME_SERVICE;
	}

	public void initialize(int roomId) throws SQLException {
		Pieces pieces = new Pieces(Pieces.initPieces());

		gameDAO.addAllPiecesById(roomId, pieces);
	}

	public void movePiece(int roomId, String sourcePosition, String targetPosition) throws SQLException {
		Pieces pieces = new Pieces(gameDAO.findAllPiecesById(roomId));
		GameManager gameManager = new GameManager(pieces, Color.valueOf(roomDAO.findTurnById(roomId).toUpperCase()));
		gameManager.moveFromTo(Position.of(sourcePosition), Position.of(targetPosition));
		roomDAO.updateTurnById(roomId, gameManager.getCurrentColor());

		gameDAO.removeAllPiecesById(roomId);
		gameDAO.addAllPiecesById(roomId, pieces);
	}

	// TODO : roomId 마다 계산해줘야 하고, DB도 따로 만들어줘야 함.
	public double getScore(int roomId, Color color) throws SQLException {
		Pieces pieces = new Pieces(gameDAO.findAllPiecesById(roomId));
		GameManager gameManager = new GameManager(pieces, color);

		return PieceScore.calculateByColor(gameManager, color);
	}

	public PiecesResponseDTO getPiecesResponseDTO(int roomId) throws SQLException {
		Pieces pieces = new Pieces(gameDAO.findAllPiecesById(roomId));
		return new PiecesResponseDTO(pieces);
	}
}
