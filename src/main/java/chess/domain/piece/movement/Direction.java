package chess.domain.piece.movement;

import java.util.Arrays;
import java.util.List;

public enum Direction {

    UP(0, 1),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),
    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),
    KNIGHT_UP_RIGHT(1, 2),
    KNIGHT_UP_LEFT(-1, 2),
    KNIGHT_RIGHT_UP(2, 1),
    KNIGHT_RIGHT_DOWN(2, -1),
    KNIGHT_DOWN_RIGHT(1, -2),
    KNIGHT_DOWN_LEFT(-1, -2),
    KNIGHT_LEFT_UP(-2, 1),
    KNIGHT_LEFT_DOWN(-2, -1);

    private final int x;
    private final int y;

    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static List<Direction> diagonalDirections() {
        return Arrays.asList(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    }

    public static List<Direction> straightDirections() {
        return Arrays.asList(UP, DOWN, RIGHT, LEFT);
    }

    public static List<Direction> allDirections() {
        return Arrays.asList(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT, UP, DOWN, RIGHT, LEFT);
    }

    public static List<Direction> whitePawnDirections() {
        return Arrays.asList(UP, UP_RIGHT, UP_LEFT);
    }

    public static List<Direction> blackPawnDirections() {
        return Arrays.asList(DOWN, DOWN_RIGHT, DOWN_LEFT);
    }

    public static List<Direction> knightDirections() {
        return Arrays.asList(KNIGHT_UP_RIGHT, KNIGHT_UP_LEFT, KNIGHT_RIGHT_UP, KNIGHT_RIGHT_DOWN,
                KNIGHT_DOWN_RIGHT, KNIGHT_DOWN_LEFT, KNIGHT_LEFT_UP, KNIGHT_LEFT_DOWN);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDiagonal() {
        return diagonalDirections().contains(this);
    }

    public boolean isStraight() {
        return straightDirections().contains(this);
    }
}
