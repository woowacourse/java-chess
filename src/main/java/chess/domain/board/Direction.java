package chess.domain.board;

public enum Direction {
    UP(1, 0),
    UP_RIGHT(1, 1),
    RIGHT(0, 1),
    DOWN_RIGHT(-1, 1),
    DOWN(-1, 0),
    DOWN_LEFT(-1, -1),
    LEFT(0, -1),
    UP_LEFT(1, -1);

    private final int vertical;
    private final int horizontal;


    Direction(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public static Direction of(int verticalDistance, int horizontalDistance) {
        if (verticalDistance > 0) {
            return createUpsideDirection(horizontalDistance);
        }
        if (verticalDistance < 0) {
            return createDownsideDirection(horizontalDistance);
        }
        return createHorizontalDirection(horizontalDistance);
    }

    private static Direction createUpsideDirection(int horizontalDistance) {
        if (horizontalDistance < 0) {
            return UP_LEFT;
        }
        if (horizontalDistance == 0) {
            return UP;
        }
        return UP_RIGHT;
    }

    private static Direction createDownsideDirection(int horizontalDistance) {
        if (horizontalDistance < 0) {
            return DOWN_LEFT;
        }
        if (horizontalDistance == 0) {
            return DOWN;
        }
        return DOWN_RIGHT;
    }

    private static Direction createHorizontalDirection(int horizontalDistance) {
        if (horizontalDistance < 0) {
            return LEFT;
        }
        return RIGHT;
    }

    public boolean isOrthogonal() {
        return this == UP || this == DOWN || this == LEFT || this == RIGHT;
    }

    public boolean isDiagonal() {
        return !isOrthogonal();
    }

    public boolean isUpside() {
        return this.vertical == 1;
    }

    public boolean isDownside() {
        return this.vertical == -1;
    }

    public boolean isRightSide() {
        return this.horizontal == 1;
    }

    public boolean isLeftSide() {
        return this.horizontal == -1;
    }
}
