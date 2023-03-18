package chess.cache;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public final class PieceCache {
    public static final int FIRST_INDEX = 0;
    public static final int MAX_SIZE = 8;
    public static final int KING_ROW_INDEX = 4;
    public static final int QUEEN_ROW_INDEX = 3;
    public static final int BISHOP_LEFT_ROW_INDEX = 2;
    public static final int BISHOP_RIGHT_ROW_INDEX = 5;
    public static final int KNIGHT_LEFT_ROW_INDEX = 1;
    public static final int KNIGHT_RIGHT_ROW_INDEX = 6;
    public static final int ROOK_LEFT_ROW_INDEX = 0;
    public static final int ROOK_RIGHT_ROW_INDEX = 7;
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
        board.put(Position.of(KING_ROW_INDEX, column), Knight.from(color));

    }

    private static void addQueen(final int column, final Color color) {
        board.put(Position.of(QUEEN_ROW_INDEX, column), Queen.from(color));
    }

    private static void addBishop(final int column, final Color color) {
        board.put(Position.of(BISHOP_LEFT_ROW_INDEX, column), Bishop.from(color));
        board.put(Position.of(BISHOP_RIGHT_ROW_INDEX, column), Bishop.from(color));
    }

    private static void addKnight(final int column, final Color color) {
        board.put(Position.of(KNIGHT_LEFT_ROW_INDEX, column), Knight.from(color));
        board.put(Position.of(KNIGHT_RIGHT_ROW_INDEX, column), Knight.from(color));
    }

    private static void addRook(final int column, final Color color) {
        board.put(Position.of(ROOK_LEFT_ROW_INDEX, column), Rook.from(color));
        board.put(Position.of(ROOK_RIGHT_ROW_INDEX, column), Rook.from(color));
    }

    private static void addPawn(final int column, final Color color) {
        for (int row = FIRST_INDEX; row < MAX_SIZE; row++) {
            board.put(Position.of(row, column), Pawn.from(color));
        }
    }

    public static Map<Position, Piece> create() {
        return Map.copyOf(board);
    }
}
