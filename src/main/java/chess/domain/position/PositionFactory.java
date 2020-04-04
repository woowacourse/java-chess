package chess.domain.position;

import chess.domain.util.WrongPositionException;

import java.util.HashMap;
import java.util.Map;

public class PositionFactory {
    private static final Map<String, Position> positionCache;

    static {
        positionCache = new HashMap<>();
        for (Row row : Row.values()) {
            createPositionBy(row);
        }
    }

    private static void createPositionBy(Row row) {
        for (Column column : Column.values()) {
            String name = row.getName() + column.getName();
            positionCache.put(name, new Position(name));
        }
    }

    public static Position of(String position) {
        validate(position);
        return positionCache.get(position);
    }

    public static Position of(Row row, Column column) {
        return of(row.getName() + column.getName());
    }

    private static void validate(String position) {
        if (!positionCache.containsKey(position)) {
            throw new WrongPositionException();
        }
    }
}
