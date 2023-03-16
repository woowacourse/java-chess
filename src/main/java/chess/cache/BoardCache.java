package chess.cache;

import chess.domain.Position;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public final class BoardCache {
    private static final Map<Position, Piece> board = new HashMap<>();

    private BoardCache() {
    }

    static {
        for (int column = 0; column < 8; column++) {
            addRow(column);
        }
    }

    private static void addRow(final int column) {
        for (int row = 0; row < 8; row++) {
            board.put(Position.of(column, row), Empty.create());
        }
    }

    public static Map<Position, Piece> create() {
        return Map.copyOf(board);
    }
}
