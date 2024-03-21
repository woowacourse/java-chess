package chess.model;

import java.util.Arrays;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UP_LEFT(-1, -1),
    UP_RIGHT(-1, 1),
    DOWN_LEFT(1, -1),
    DOWN_RIGHT(1, 1),
    NONE(0, 0);

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public static Direction findDirection(Position source, Position target) {
        int deltaX = calculateDifference(source.getRow(), target.getRow());
        int deltaY = calculateDifference(source.getColumn(), target.getColumn());
        if (Math.abs(deltaY) - Math.abs(deltaX) != 0 && deltaX != 0 && deltaY != 0) {
            return NONE;
        }
        int normalizedX = normalize(deltaX);
        int normalizedY = normalize(deltaY);
        return Arrays.stream(values())
                .filter(direction -> direction.deltaX == normalizedX && direction.deltaY == normalizedY)
                .findAny()
                .orElse(NONE);
    }

    private static int calculateDifference(int source, int target) {
        return target - source;
    }

    private static int normalize(int delta) {
        if (delta == 0) {
            return 0;
        }
        return delta / Math.abs(delta);
    }
}
