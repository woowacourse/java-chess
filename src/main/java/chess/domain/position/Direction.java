package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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

    private static final List<Direction> DIAGONAL_DIRECTIONS = List.of(Direction.NE, Direction.SE, Direction.NW, Direction.SW);

    private final int x;
    private final int y;
    
    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    static Direction findByUnitVector(int x, int y) {
        return Arrays.stream(values())
                .filter(value -> value.x == x)
                .filter(value -> value.y == y)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("해당 방향이 존재하지 않습니다"));
    }

    public static boolean isDiagonal(Direction direction) {
        return DIAGONAL_DIRECTIONS.contains(direction);
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
}
