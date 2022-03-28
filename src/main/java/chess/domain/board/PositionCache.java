package chess.domain.board;

import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class PositionCache {

    private static final Map<String, Position> CACHE = new LinkedHashMap<>();

    static {
        for (int row : Chessboard.SIZE) {
            initColumn(row);
        }
    }

    private static void initColumn(int row) {
        for (int column : Chessboard.SIZE) {
            CACHE.put(Integer.toString(row) + column, new Position(row, column));
        }
    }

    public static Position of(int row, int column) {
        return CACHE.get(Integer.toString(row) + column);
    }

    public static Map<String, Position> create() {
        return new LinkedHashMap<>(CACHE);
    }
}
