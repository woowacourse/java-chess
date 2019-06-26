package chess.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import chess.dao.ChessGameDAO;
import chess.dao.PieceDAO;
import chess.database.DatabaseConnection;
import chess.domain.ChessGame;
import chess.domain.ChessPiece;
import chess.domain.Player;
import chess.domain.Position;
import chess.domain.piece.Piece;

public class ChessGameService {
    public static List<String> getPieceImages(ChessGame chessGame) {
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
