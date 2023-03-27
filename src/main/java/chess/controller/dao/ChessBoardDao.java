package chess.controller.dao;

import chess.domain.ChessGame;
import chess.domain.board.*;
import chess.domain.piece.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardDao {

    DBConnection dbConnection = new DBConnection();

    public Board findAll() {
        final String queryGetPieces = "select * from piece;";
        try (
                final var connection = dbConnection.getConnection();
                final var preparedStatement = connection.prepareStatement(queryGetPieces)) {
            ResultSet resultSetForBoard = preparedStatement.executeQuery();
            if (resultSetForBoard.next()) {
                Map<Square, Piece> board = new HashMap<>();
                while (resultSetForBoard.next()) {
                    String file = resultSetForBoard.getString(3);
                    String rank = resultSetForBoard.getString(4);
                    Color color = Color.valueOf(resultSetForBoard.getString(5));
                    String type = resultSetForBoard.getString(6);
                    Square square = new Square(File.valueOf(file), Rank.valueOf(rank));
                    Piece piece = createPiece(type, color);
                    board.put(square, piece);
                }
                return new Board(board);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Piece createPiece(String type, Color color) {
        if (type == "PAWN") {
            return new Pawn(color);
        } else if (type == "KNIGHT") {
            return new Knight(color);
        } else if (type == "KING") {
            return new King(color);
        } else if (type == "ROOK") {
            return new Rook(color);
        } else if (type == "BISHOP") {
            return new Bishop(color);
        } else if (type == "QUEEN") {
            return new Queen(color);
        }
        return null;
    }

}
