package chess.position;

import java.util.HashMap;
import java.util.Map;

public class PositionCache {
    private static final Map<String, Position> positionCache = new HashMap<>();

    static {
        for (int horizontal = 97; horizontal <= 104; horizontal++) {
            circuitVertical(horizontal);
        }
    }

    private static void circuitVertical(int horizontal) {
        for (int vertical = 1; vertical <= 8; vertical++) {
            positionCache.put(String.valueOf((char) horizontal) + vertical,
                    Position.initPosition(horizontal - 96, vertical));
        }
    }

    public static Position findPosition(String command) {
        return positionCache.get(command);
    }
}
