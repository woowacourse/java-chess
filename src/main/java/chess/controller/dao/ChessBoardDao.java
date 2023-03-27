package chess.controller.dao;

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
            final var preparedStatement = connection.prepareStatement(queryGetPieces)
        ) {
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
            throw new IllegalArgumentException("현재 저장된 체스 보드가 없습니다.");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Piece createPiece(String type, Color color) {
        switch (type) {
            case "Pawn":
                return new Pawn(color);
            case "Knight":
                return new Knight(color);
            case "King":
                return new King(color);
            case "Rook":
                return new Rook(color);
            case "Bishop":
                return new Bishop(color);
            case "Queen":
                return new Queen(color);
        }
        return new Empty(color);
    }

}
