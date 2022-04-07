package chess.domain.position;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChessBoardPosition {

    private static final Map<String, Position> CACHE = new HashMap<>();

    static {
        for (Column column : Column.orderedValues()) {
            cacheByFile(column);
        }
    }

    private static void cacheByFile(Column column) {
        for (Row row : Row.values()) {
            CACHE.put(column.name().toLowerCase(Locale.ROOT) + row.getValue(),
                new Position(column, row));
        }
    }

    public static Position from(String key) {
        if (!CACHE.containsKey(key.toLowerCase(Locale.ROOT))) {
            throw new IllegalArgumentException("체스 보드 position의 범위를 넘어갑니다.");
        }
        return CACHE.get(key.toLowerCase(Locale.ROOT));
    }
}
