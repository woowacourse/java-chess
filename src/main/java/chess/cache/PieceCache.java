package chess.cache;

import chess.Color;
import chess.Piece;
import chess.PieceType;
import chess.Position;

import java.util.HashMap;
import java.util.Map;

public class PieceCache {
    private static final Map<Position, Piece> board = new HashMap<>();

    private PieceCache() {
    }

    static {
        addPawn(1, Color.BLACK);
        addRook(0, Color.BLACK);
        addKnight(0, Color.BLACK);
        addBishop(0, Color.BLACK);
        addQueen(0, 3, Color.BLACK);
        addKing(0, 4, Color.BLACK);

        addPawn(6, Color.WHITE);
        addRook(7, Color.WHITE);
        addKnight(7, Color.WHITE);
        addBishop(7, Color.WHITE);
        addQueen(7, 4, Color.WHITE);
        addKing(7, 3, Color.WHITE);
    }

    private static void addKing(final int column, final int row, final Color color) {
        board.put(new Position(column, row), new Piece(PieceType.KING, color));

    }

    private static void addQueen(final int column, final int row, final Color color) {
        board.put(new Position(column, row), new Piece(PieceType.QUEEN, color));
    }

    private static void addBishop(final int column, final Color color) {
        board.put(new Position(column, 2), new Piece(PieceType.BISHOP, color));
        board.put(new Position(column, 5), new Piece(PieceType.BISHOP, color));
    }

    private static void addKnight(final int column, final Color color) {
        board.put(new Position(column, 1), new Piece(PieceType.KNIGHT, color));
        board.put(new Position(column, 6), new Piece(PieceType.KNIGHT, color));
    }

    private static void addRook(final int column, final Color color) {
        board.put(new Position(column, 0), new Piece(PieceType.ROOKS, color));
        board.put(new Position(column, 7), new Piece(PieceType.ROOKS, color));
    }

    private static void addPawn(final int column, final Color color) {
        for (int row = 0; row < 8; row++) {
            board.put(new Position(column, row), new Piece(PieceType.PAWN, color));
        }
    }

    public static Map<Position, Piece> create() {
        return Map.copyOf(board);
    }
}
