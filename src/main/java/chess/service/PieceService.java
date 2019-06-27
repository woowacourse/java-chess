package chess.service;

import java.sql.SQLException;
import java.util.List;

import chess.dao.PieceDAO;
import chess.domain.piece.Piece;

public class PieceService {
	public void saveInitialPiece(int roomNumber, List<Piece> pieces) throws SQLException {
		PieceDAO pieceDAO = new PieceDAO();
		pieceDAO.addAllPieces(roomNumber, pieces);
	}

	public void savePiece(int roomNumber, List<Piece> pieces) throws SQLException {
		PieceDAO pieceDAO = new PieceDAO();
		pieceDAO.deleteAllPieces(roomNumber);
		pieceDAO.addAllPieces(roomNumber, pieces);
	}

	public List<Piece> loadChessPieces(int roomNumber) throws SQLException {
		PieceDAO pieceDAO = new PieceDAO();
		return pieceDAO.getChessPieces(roomNumber);
	}

}
