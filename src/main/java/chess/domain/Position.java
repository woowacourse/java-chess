package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class Position {

    private final String value;

    private Position(String value) {
        this.value = value;
    }

    public static Position of(String value) {
        return PositionCache.getCache(value);
    }

    @Override
    public String toString() {
        return "Position{" + "value='" + value + '\'' + '}';
    }

    private static class PositionCache {

        static final Map<String, Position> cache = new HashMap<>();

        static Position getCache(String value) {
            return cache.computeIfAbsent(value, Position::new);
        }
    }
}
