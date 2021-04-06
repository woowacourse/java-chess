package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PieceDirection {
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
    UP_UP_LEFT(-1, 2),
    UP_LEFT_LEFT(-2, 1),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_RIGHT_RIGHT(2, -1),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_LEFT_LEFT(-2, -1);
    private final int column;
    private final int row;

    PieceDirection(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public static List<PieceDirection> diagonalDirections() {
        return Arrays.asList(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);
    }

    public static List<PieceDirection> straightDirections() {
        return Arrays.asList(UP, DOWN, LEFT, RIGHT);
    }

    public static List<PieceDirection> aroundDirections() {
        ArrayList<PieceDirection> aroundPieceDirections = new ArrayList<>(diagonalDirections());
        aroundPieceDirections.addAll(straightDirections());
        return aroundPieceDirections;
    }

    public static List<PieceDirection> knightDirections() {
        return Arrays.asList(
            UP_UP_LEFT, UP_UP_RIGHT, UP_RIGHT_RIGHT, UP_LEFT_LEFT,
            DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT, DOWN_RIGHT_RIGHT, DOWN_LEFT_LEFT);
    }

    public static List<PieceDirection> forwardDirection(TeamColor teamColor) {
        if (teamColor.isWhite()) {
            return Collections.singletonList(UP);
        }
        return Collections.singletonList(DOWN);
    }

    public static List<PieceDirection> forwardDiagonal(TeamColor teamColor) {
        if (teamColor.isWhite()) {
            return Arrays.asList(UP_LEFT, UP_RIGHT);
        }
        return Arrays.asList(DOWN_LEFT, DOWN_RIGHT);
    }

    public int column() {
        return column;
    }

    public int row() {
        return row;
    }
}