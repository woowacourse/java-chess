package chess.cache;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class BoardCache {
    private static final Map<Position, Piece> board = new HashMap<>();

    private BoardCache() {
    }

    static {
        Arrays.stream(Column.values())
                .forEach(column -> Arrays.stream(Row.values())
                        .forEach(row -> board.put(Position.of(row, column), Empty.create())));
    }

    public static Map<Position, Piece> create() {
        return Map.copyOf(board);
    }
}
