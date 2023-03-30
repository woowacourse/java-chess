package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(-1, 1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1);

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

    public static boolean isUpsideDiagonal(final int file, final int rank) {
        return (file == UP_LEFT.fileMove || file == UP_RIGHT.fileMove) && rank == UP.rankMove;
    }

    public static boolean isDownsideDiagonal(final int file, final int rank) {
        return (file == DOWN_LEFT.fileMove || file == DOWN_RIGHT.fileMove) && rank == DOWN.rankMove;
    }

    public Position calculate(final Position before) {
        return before.calculate(this.rankMove, this.fileMove);
    }
}
