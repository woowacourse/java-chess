package chess.domain.position;

import java.util.Arrays;

public enum Direction {
    N(0, 1),
    E(1, 0),
    W(-1, 0),
    S(0, -1),
    NE(1, 1),
    SE(1, -1),
    SW(-1, -1),
    NW(-1, 1),
    NNE(1, 2),
    ENE(2, 1),
    ESE(2, -1),
    SSE(1, -2),
    SSW(-1, -2),
    WSW(-2, -1),
    WNW(-2, 1),
    NNW(-1, 2);
    
    
    public static final String NO_SUCH_DIRECTION_ERROR_MESSAGE = "[DIRECTION ERROR] 해당하는 방향이 없습니다.";
    private final int x;
    private final int y;
    
    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    static Direction findByVector(int x, int y) {
        return Arrays.stream(values())
                .filter(value -> value.x == x)
                .filter(value -> value.y == y)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_DIRECTION_ERROR_MESSAGE));
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
}
