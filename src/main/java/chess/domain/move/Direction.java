package chess.domain.move;

import chess.domain.piece.Position;

import java.util.List;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(-1, 1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1),
    UP_TWO_LEFT(-1, 2),
    UP_TWO_RIGHT(1, 2),
    DOWN_TWO_LEFT(-1, -2),
    DOWN_TWO_RIGHT(1, -2);

    private final int fileMove;
    private final int rankMove;

    Direction(final int fileMove, final int rankMove) {
        this.fileMove = fileMove;
        this.rankMove = rankMove;
    }

    public static List<Direction> getAllDirections() {
        return List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);
    }

    public static List<Direction> getFourDirections() {
        return List.of(UP, DOWN, LEFT, RIGHT);
    }

    public static List<Direction> getDiagonalDirections() {
        return List.of(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);
    }

    public static List<Direction> getLShapeDirections() {
        return List.of(UP_TWO_LEFT, UP_TWO_RIGHT, DOWN_TWO_LEFT, DOWN_TWO_RIGHT);
    }

    public Position calculate(final Position before) {
        return before.calculate(this.rankMove, this.fileMove);
    }
}
