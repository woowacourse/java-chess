package chess.utils;

import chess.position.Position;
import chess.position.PositionCache;

import java.util.HashMap;
import java.util.Map;

public class PositionParser {
    private static final Map<String, Integer> RANKS = new HashMap<>();
    private static final Map<String, Integer> FILES = new HashMap<>();
    private static final String WRONG_POSITION = "올바르지 않은 위치 정보입니다.";

    static {
        RANKS.put("a", 0);
        RANKS.put("b", 1);
        RANKS.put("c", 2);
        RANKS.put("d", 3);
        RANKS.put("e", 4);
        RANKS.put("f", 5);
        RANKS.put("g", 6);
        RANKS.put("h", 7);

        FILES.put("8", 0);
        FILES.put("7", 1);
        FILES.put("6", 2);
        FILES.put("5", 3);
        FILES.put("4", 4);
        FILES.put("3", 5);
        FILES.put("2", 6);
        FILES.put("1", 7);
    }

    public static Position parse(String x, String y) {
        if (!RANKS.containsKey(x) || !FILES.containsKey(y)) {
            throw new IllegalArgumentException(WRONG_POSITION);
        }

        return PositionCache.of(FILES.get(y), RANKS.get(x));
    }
}
