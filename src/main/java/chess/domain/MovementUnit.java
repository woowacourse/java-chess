package chess.domain;

import java.util.stream.Stream;

public enum MovementUnit {
    UP(0, 1),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),

    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),

    KNIGHT_UP_RIGHT(1, 2),
    KNIGHT_RIGHT_UP(2, 1),
    KNIGHT_UP_LEFT(-1, 2),
    KNIGHT_LEFT_UP(-2, 1),
    KNIGHT_DOWN_RIGHT(1, -2),
    KNIGHT_RIGHT_DOWN(2, -1),
    KNIGHT_DOWN_LEFT(-1, -2),
    KNIGHT_LEFT_DOWN(-2, -1),

    NOT_MOVE(0, 0);

    private int movementX;
    private int movementY;

    MovementUnit(int movementX, int movementY) {
        this.movementX = movementX;
        this.movementY = movementY;
    }

    public static MovementUnit direction(int movementX, int movementY) {
        if (movementX == 0 && movementY == 0) {
            return NOT_MOVE;
        }

        if (movementY == 0) {
            return 0 < movementX ? RIGHT : LEFT;
        }

        if (movementX == 0) {
            return 0 < movementY ? UP : DOWN;
        }

        if (movementX == movementY) {
            return 0 < movementX ? UP_RIGHT : DOWN_LEFT;
        }

        if (movementX + movementY == 0) {
            return 0 < movementX ? DOWN_RIGHT : UP_LEFT;
        }

        return Stream.of(values())
                .filter(direction -> direction.movementX == movementX && direction.movementY == movementY)
                .findFirst()
                .orElse(NOT_MOVE);
    }

    int nextXPoint(int x) {
        return this.movementX + x;
    }

    int nextYPoint(int y) {
        return this.movementY + y;
    }
}