package chess.domain.board;

import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Piece;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.Row;

public class BoardFactory {

    void initialize(Board board) {
        initializeBlackTeam(board);
        initializeWhiteTeam(board);
    }

    private void initializeBlackTeam(Board board) {
        initializePawn(board, Row.SEVEN, Color.BLACK);
        initializeHighValuePiece(board, Row.EIGHT, Color.BLACK);
    }

    private void initializeWhiteTeam(Board board) {
        initializePawn(board, Row.TWO, Color.WHITE);
        initializeHighValuePiece(board, Row.ONE, Color.WHITE);
    }

    private void initializePawn(Board board, Row row, Color color) {
        for (Column column : Column.values()) {
            Position position = new Position(row, column);
            if (color == Color.WHITE) {
                board.put(position, new Piece(PieceType.WHITE_PAWN, color));
            }
            if (color == Color.BLACK) {
                board.put(position, new Piece(PieceType.BLACK_PAWN, color));
            }
        }
    }

    private void initializeHighValuePiece(Board board, Row row, Color color) {
        board.put(new Position(row, Column.A), new Piece(PieceType.ROOK, color));
        board.put(new Position(row, Column.H), new Piece(PieceType.ROOK, color));

        board.put(new Position(row, Column.B), new Piece(PieceType.KNIGHT, color));
        board.put(new Position(row, Column.G), new Piece(PieceType.KNIGHT, color));

        board.put(new Position(row, Column.C), new Piece(PieceType.BISHOP, color));
        board.put(new Position(row, Column.F), new Piece(PieceType.BISHOP, color));

        board.put(new Position(row, Column.D), new Piece(PieceType.QUEEN, color));
        board.put(new Position(row, Column.E), new Piece(PieceType.KING, color));
    }
}
