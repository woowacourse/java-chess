package chess.domain.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Position {

    private static final Map<String, Position> CACHE;

    private X x;
    private Y y;

    private Position(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    static {
        Map<String, Position> positions = new HashMap<>();

        for (X x : X.values()) {
            createPositionsByY(positions, x);
        }
        CACHE = Collections.unmodifiableMap(positions);
    }

    private static void createPositionsByY(Map<String, Position> positions, X x) {
        for (Y y : Y.values()) {
            positions.put(getKey(x, y), new Position(x, y));
        }
    }

    private static String getKey(X x, Y y) {
        return x.toString() + y.toString();
    }

    public static List<Position> values() {
        return Collections.unmodifiableList(new ArrayList<>(CACHE.values()));
    }

    public static Position of(X x, Y y) {
        return of(x.toString() + y.toString());
    }

    public static Position of(String expression) {
        return CACHE.get(expression);
    }
}
