package chess.model.position;

import java.util.Arrays;

public enum Movement {
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

    Movement(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public static Movement findMovement(Position source, Position target) {
        int deltaX = differenceRow(source, target);
        int deltaY = differenceColumn(source, target);
        return findValue(deltaX, deltaY);
    }

    private static int differenceRow(Position source, Position target) {
        Row sourceRow = source.getRow();
        Row targetRow = target.getRow();
        return targetRow.getIndex() - sourceRow.getIndex();
    }

    private static int differenceColumn(Position source, Position target) {
        Column sourceColumn = source.getColumn();
        Column targetColumn = target.getColumn();
        return targetColumn.getIndex() - sourceColumn.getIndex();
    }

    private static Movement findValue(int deltaX, int deltaY) {
        return Arrays.stream(values())
            .filter(movement -> movement.deltaX == deltaX && movement.deltaY == deltaY)
            .findAny()
            .orElse(NONE);
    }

    public static Movement findDirection(Position source, Position target) {
        int deltaX = differenceRow(source, target);
        int deltaY = differenceColumn(source, target);
        if (deltaX != 0 && deltaY != 0 && Math.abs(deltaY) - Math.abs(deltaX) != 0) {
            return NONE;
        }
        int normalizedX = normalize(deltaX);
        int normalizedY = normalize(deltaY);
        return findValue(normalizedX, normalizedY);
    }

    private static int normalize(int delta) {
        if (delta == 0) {
            return 0;
        }
        return delta / Math.abs(delta);
    }
}
