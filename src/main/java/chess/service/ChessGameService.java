package chess.service;

import java.sql.SQLException;
import java.util.*;

import chess.dao.ChessGameDAO;
import chess.domain.ChessGame;
import chess.domain.ChessPiece;
import chess.domain.Player;
import chess.domain.Position;
import chess.domain.piece.Piece;

public class ChessGameService {
	public List<String> getPieceImages(ChessGame chessGame) {
		Map<Position, String> positionPieceImages = new LinkedHashMap<>();

		for (int y = 8; y >= 1; y--) {
			for (int x = 1; x <= 8; x++) {
				positionPieceImages.put(Position.getPosition(x, y), ChessPiece.EMPTY.getImage());
			}
		}

		for (Piece piece : chessGame.getPieces()) {
			positionPieceImages.put(piece.getPosition(), piece.getPieceImage());
		}

		return new ArrayList<>(positionPieceImages.values());
	}

	public int saveInitialChessGame(String currentPlayerName) throws SQLException {
		ChessGameDAO chessGameDAO = new ChessGameDAO();
		return chessGameDAO.addChessGame(currentPlayerName);
	}

	public int saveChessGame(int roomNumber, ChessGame chessGame) throws SQLException {
		ChessGameDAO chessGameDAO = new ChessGameDAO();
		chessGameDAO.changeTurn(roomNumber, chessGame.getCurrentPlayerName());
		return roomNumber;
	}

	public Player loadTurn(int roomNumber) throws SQLException {
		ChessGameDAO chessGameDAO = new ChessGameDAO();
		return chessGameDAO.getChessGameTurn(roomNumber);
	}

	public List<Integer> getRoomNumbers() throws SQLException {
		ChessGameDAO chessGameDAO = new ChessGameDAO();
		return chessGameDAO.getNotOverAllRoomNumbers();
	}

	public void gameOver(int roomNumber) throws SQLException {
		ChessGameDAO chessGameDAO = new ChessGameDAO();
		chessGameDAO.gameover(roomNumber);
	}
}
