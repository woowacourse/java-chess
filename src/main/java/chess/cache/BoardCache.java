package chess.cache;

import chess.domain.Position;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public final class BoardCache {
    public static final int FIRST_INDEX = 0;
    public static final int MAX_SIZE = 8;
    private static final Map<Position, Piece> board = new HashMap<>();

    private BoardCache() {
    }

    static {
        for (int column = FIRST_INDEX; column < MAX_SIZE; column++) {
            addRow(column);
        }
    }

    private static void addRow(final int column) {
        for (int row = FIRST_INDEX; row < MAX_SIZE; row++) {
            board.put(Position.of(column, row), Empty.create());
        }
    }

    public static Map<Position, Piece> create() {
        return Map.copyOf(board);
    }
}
