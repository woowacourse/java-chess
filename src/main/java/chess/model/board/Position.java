package chess.model.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Position {
    private static final Map<Integer, Position> POSITION_CACHE = new HashMap<>();

    static {
        for (int x = Board.MIN_LENGTH; x <= Board.MAX_LENGTH; x++) {
            addPositionsBy(x);
        }
    }

    private static void addPositionsBy(int x) {
        for (int y = Board.MIN_LENGTH; y <= Board.MAX_LENGTH; y++) {
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

    public static List<Position> values() {
        return List.copyOf(POSITION_CACHE.values());
    }
}
