package chess.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import chess.dao.PieceDAO;
import chess.database.DatabaseConnection;
import chess.domain.piece.Piece;

public class PieceService {
	public void saveInitialPiece(int roomNumber, List<Piece> pieces) throws SQLException {
		try (Connection connection = DatabaseConnection.getConnection()) {
			PieceDAO pieceDAO = PieceDAO.getInstance(connection);
			pieceDAO.addAllPieces(roomNumber, pieces);
		}
	}

	public void savePiece(int roomNumber, List<Piece> pieces) throws SQLException {
		try (Connection connection = DatabaseConnection.getConnection()) {
			PieceDAO pieceDAO = PieceDAO.getInstance(connection);
			pieceDAO.deleteAllPieces(roomNumber);
			pieceDAO.addAllPieces(roomNumber, pieces);
		}
	}

	public List<Piece> loadChessPieces(int roomNumber) throws SQLException {
		try (Connection connection = DatabaseConnection.getConnection()) {
			PieceDAO pieceDAO = PieceDAO.getInstance(connection);
			return pieceDAO.getChessPieces(roomNumber);
		}
	}

}
