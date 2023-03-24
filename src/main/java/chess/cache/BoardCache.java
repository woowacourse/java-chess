package chess.cache;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public final class BoardCache {
    private static final Map<Position, Piece> board = new HashMap<>();

    private BoardCache() {
    }

    static {
        for (final Column column : Column.values()) {
            addRow(column);
        }
    }

    private static void addRow(final Column column) {
        for (Row row : Row.values()) {
            board.put(Position.of(row, column), Empty.create());
        }
    }

    public static Map<Position, Piece> create() {
        return Map.copyOf(board);
    }
}
