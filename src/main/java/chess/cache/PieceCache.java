package chess.cache;

import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.Column.*;
import static chess.domain.Row.*;

public final class PieceCache {
    private static final Map<Position, Piece> board = new HashMap<>();

    private PieceCache() {
    }

    static {
        addPawn(COLUMN_1, BLACK);
        addRook(COLUMN_0, BLACK);
        addKnight(COLUMN_0, BLACK);
        addBishop(COLUMN_0, BLACK);
        addQueen(COLUMN_0, BLACK);
        addKing(COLUMN_0, BLACK);

        addPawn(COLUMN_6, WHITE);
        addRook(COLUMN_7, WHITE);
        addKnight(COLUMN_7, WHITE);
        addBishop(COLUMN_7, WHITE);
        addQueen(COLUMN_7, WHITE);
        addKing(COLUMN_7, WHITE);
    }

    private static void addKing(final Column column, final Color color) {
        board.put(Position.of(ROW_4, column), King.from(color));

    }

    private static void addQueen(final Column column, final Color color) {
        board.put(Position.of(ROW_3, column), Queen.from(color));
    }

    private static void addBishop(final Column column, final Color color) {
        board.put(Position.of(ROW_2, column), Bishop.from(color));
        board.put(Position.of(ROW_5, column), Bishop.from(color));
    }

    private static void addKnight(final Column column, final Color color) {
        board.put(Position.of(ROW_1, column), Knight.from(color));
        board.put(Position.of(ROW_6, column), Knight.from(color));
    }

    private static void addRook(final Column column, final Color color) {
        board.put(Position.of(ROW_0, column), Rook.from(color));
        board.put(Position.of(ROW_7, column), Rook.from(color));
    }

    private static void addPawn(final Column column, final Color color) {
        for (final Row row : Row.values()) {
            board.put(Position.of(row, column), Pawn.from(color));
        }
    }

    public static Map<Position, Piece> create() {
        return Map.copyOf(board);
    }
}
