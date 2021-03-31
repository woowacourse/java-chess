package chess.domain.piece.direction;

import chess.domain.TeamColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    UP_UP_RIGHT(1, 2),
    UP_RIGHT_RIGHT(2, 1),
    UP_UP_LEFT(1, -2),
    UP_LEFT_LEFT(-2, 1),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_RIGHT_RIGHT(2, -1),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_LEFT_LEFT(-2, -1);
    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static List<Direction> diagonalDirections() {
        return Arrays.asList(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    }

    public static List<Direction> straightDirections() {
        return Arrays.asList(UP, DOWN, LEFT, RIGHT);
    }

    public static List<Direction> aroundDirections() {
        ArrayList<Direction> aroundDirections = new ArrayList<>(diagonalDirections());
        aroundDirections.addAll(straightDirections());
        return aroundDirections;
    }

    public static List<Direction> knightDirections() {
        return Arrays.asList(
                UP_UP_LEFT, UP_UP_RIGHT, UP_RIGHT_RIGHT, UP_LEFT_LEFT,
                DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT, DOWN_RIGHT_RIGHT, DOWN_LEFT_LEFT);
    }

    public static List<Direction> forwardDirection(TeamColor teamColor) {
        if (teamColor == TeamColor.WHITE) {
            return Collections.singletonList(UP);
        }
        return Collections.singletonList(DOWN);
    }

    public static List<Direction> forwardDiagonal(TeamColor teamColor) {
        if (teamColor == TeamColor.WHITE) {
            return Arrays.asList(UP_LEFT, UP_RIGHT);
        }
        return Arrays.asList(DOWN_LEFT, DOWN_RIGHT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
