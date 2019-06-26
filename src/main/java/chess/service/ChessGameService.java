package chess.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import chess.dao.ChessGameDAO;
import chess.dao.PieceDAO;
import chess.database.DatabaseConnection;
import chess.domain.ChessGame;
import chess.domain.Player;
import chess.domain.piece.Piece;

public class ChessGameService {
    public static int saveInitialChessGame(ChessGame chessGame) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            ChessGameDAO chessGameDAO = ChessGameDAO.getInstance(connection);
            Player currentPlayer = chessGame.getCurrentPlayer();
            int roomNumber = chessGameDAO.addChessGame(currentPlayer);

            PieceDAO pieceDAO = PieceDAO.getInstance(connection);
            List<Piece> pieces = chessGame.getPieces();
            pieceDAO.addAllPieces(roomNumber, pieces);
            return roomNumber;
        }
    }

    public static int saveChessGame(int roomNumber, ChessGame chessGame) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            ChessGameDAO chessGameDAO = ChessGameDAO.getInstance(connection);
            Player currentPlayer = chessGame.getCurrentPlayer();
            chessGameDAO.changeTurn(roomNumber, currentPlayer);

            PieceDAO pieceDAO = PieceDAO.getInstance(connection);
            List<Piece> pieces = chessGame.getPieces();
            pieceDAO.deleteAllPieces(roomNumber);

            pieceDAO.addAllPieces(roomNumber, pieces);
            return roomNumber;
        }
    }

    public static Player loadTurn(int roomNumber) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            ChessGameDAO chessGameDAO = ChessGameDAO.getInstance(connection);
            return chessGameDAO.getChessGameTurn(roomNumber);
        }
    }

    public static List<Piece> loadChessPieces(int roomNumber) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PieceDAO pieceDAO = PieceDAO.getInstance(connection);
            return pieceDAO.getChessPieces(roomNumber);
        }
    }

    public static List<Integer> getRoomNumbers() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            ChessGameDAO chessGameDAO = ChessGameDAO.getInstance(connection);
            return chessGameDAO.getNotOverAllRoomNumbers();
        }
    }

    public static void gameOver(int roomNumber) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            ChessGameDAO chessGameDAO = ChessGameDAO.getInstance(connection);
            chessGameDAO.gameover(roomNumber);
        }
    }
}
