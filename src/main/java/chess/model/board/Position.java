package chess.model.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    private static final int MAX_BOARD_POSITION = 8;
    private static final int MIN_BOARD_POSITION = 1;
    private static final Map<Integer, Position> POSITION_CACHE = new HashMap<>();

    static {
        for (int x = MIN_BOARD_POSITION; x <= MAX_BOARD_POSITION; x++) {
            addPositionsBy(x);
        }
    }

    private static void addPositionsBy(int x) {
        for (int y = MIN_BOARD_POSITION; y <= MAX_BOARD_POSITION; y++) {
            POSITION_CACHE.put(convertToKey(x, y), new Position(x, y));
        }
    }

    private static int convertToKey(int x, int y) {
        return Objects.hash(x, y);
    }

    private final int x;

    private final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position from(int x, int y) {
        int key = convertToKey(x, y);
        if (!POSITION_CACHE.containsKey(key)) {
            throw new IllegalArgumentException("체스판 범위를 벗어난 좌표값입니다.");
        }
        return POSITION_CACHE.get(key);
    }
}
