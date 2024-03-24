package chess.model.position;

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

    UP_UP_LEFT(-2, -1),
    UP_UP_RIGHT(-2, 1),
    DOWN_DOWN_LEFT(2, -1),
    DOWN_DOWN_RIGHT(2, 1),
    LEFT_LEFT_UP(-1, -2),
    LEFT_LEFT_DOWN(1, -2),
    RIGHT_RIGHT_UP(-1, 2),
    RIGHT_RIGHT_DOWN(1, 2),

    UP_UP(-2, 0),
    DOWN_DOWN(2, 0),

    NONE(0, 0);

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public static Direction findDirectionByDelta(Position source, Position target) {
        int deltaX = differenceRow(source.getRow(), target.getRow());
        int deltaY = differenceColumn(source.getColumn(), target.getColumn());
        return Arrays.stream(values())
            .filter(direction -> direction.deltaX == deltaX && direction.deltaY == deltaY)
            .findAny()
            .orElse(NONE);
    }

    public static Direction findDirection(Position source, Position target) {
        int deltaX = differenceRow(source.getRow(), target.getRow());
        int deltaY = differenceColumn(source.getColumn(), target.getColumn());
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

    private static int differenceRow(Row source, Row target) {
        return target.getIndex() - source.getIndex();
    }

    private static int differenceColumn(Column source, Column target) {
        return target.getIndex() - source.getIndex();
    }

    private static int normalize(int delta) {
        if (delta == 0) {
            return 0;
        }
        return delta / Math.abs(delta);
    }
}
