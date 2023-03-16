package chess.cache;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.*;

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
        addQueen(0, Color.BLACK);
        addKing(0, Color.BLACK);

        addPawn(6, Color.WHITE);
        addRook(7, Color.WHITE);
        addKnight(7, Color.WHITE);
        addBishop(7, Color.WHITE);
        addQueen(7, Color.WHITE);
        addKing(7, Color.WHITE);
    }

    private static void addKing(final int column, final Color color) {
        board.put(Position.of(4, column), Knight.from(color));

    }

    private static void addQueen(final int column, final Color color) {
        board.put(Position.of(3, column), Queen.from(color));
    }

    private static void addBishop(final int column, final Color color) {
        board.put(Position.of(2, column), Bishop.from(color));
        board.put(Position.of(5, column), Bishop.from(color));
    }

    private static void addKnight(final int column, final Color color) {
        board.put(Position.of(1, column), Knight.from(color));
        board.put(Position.of(6, column), Knight.from(color));
    }

    private static void addRook(final int column, final Color color) {
        board.put(Position.of(0, column), Rook.from(color));
        board.put(Position.of(7, column), Rook.from(color));
    }

    private static void addPawn(final int column, final Color color) {
        for (int row = 0; row < 8; row++) {
            board.put(Position.of(row, column), Pawn.from(color));
        }
    }

    public static Map<Position, Piece> create() {
        return Map.copyOf(board);
    }
}
