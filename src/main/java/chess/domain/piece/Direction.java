package chess.domain.piece;

import java.util.Arrays;
import java.util.Set;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    LEFT_UP(-1, 1),
    RIGHT_UP(1, 1),
    LEFT_DOWN(-1, -1),
    RIGHT_DOWN(1, -1),
    UP_UP(0, 2),
    DOWN_DOWN(0, -2),

    UP_UP_LEFT(-1, 2),
    UP_UP_RIGHT(1, 2),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_DOWN_RIGHT(1, -2),
    UP_LEFT_LEFT(-2, 1),
    DOWN_LEFT_LEFT(-2, -1),
    UP_RIGHT_RIGHT(2, 1),
    DOWN_RIGHT_RIGHT(2, -1);

    private final int dx;
    private final int dy;

    Direction(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Set<Direction> getFourDirection() {
        return Set.of(UP, DOWN, LEFT, RIGHT);
    }

    public static Set<Direction> getDiagonalDirection() {
        return Set.of(LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN);
    }

    public static Set<Direction> getEightDirection() {
        return Set.of(UP, DOWN, LEFT, RIGHT, LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN);
    }

    public static Set<Direction> getKnightDirection() {
        return Set.of(UP_UP_LEFT, UP_UP_RIGHT, DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT,
                UP_LEFT_LEFT, DOWN_LEFT_LEFT, UP_RIGHT_RIGHT, DOWN_RIGHT_RIGHT
        );
    }

    public static Set<Direction> getWhiteFirstPawnDirection() {
        return Set.of(Direction.UP_UP, Direction.UP, Direction.LEFT_UP, Direction.RIGHT_UP);
    }

    public static Set<Direction> getBlackFirstPawnDirection() {
        return Set.of(Direction.DOWN_DOWN, Direction.DOWN, Direction.LEFT_DOWN, Direction.RIGHT_DOWN);
    }

    public static Direction findDirection(final int dx, final int dy) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.dx == dx && direction.dy == dy)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("해당 방향이 없음"));
    }


    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
