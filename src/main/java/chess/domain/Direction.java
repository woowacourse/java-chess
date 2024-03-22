package chess.domain;

import chess.domain.position.Position;

import java.util.Set;

public enum Direction {
    DOWN,
    LEFT,
    RIGHT,
    TOP_RIGHT,
    TOP_LEFT,
    DOWN_RIGHT,
    DOWN_LEFT,
    TOP;

    public static Direction of(final Position source, final Position target) {
        if (source.indexOfFile() == target.indexOfFile()) {
            return calculateTD(source, target);
        }
        if (source.indexOfRank() == target.indexOfRank()) {
            return calculateLR(source, target);
        }
        if (source.calculateFileDistanceTo(target) == source.calculateRankDistanceTo(target)) {
            return calculateDiagonal(source, target);
        }
        throw new IllegalArgumentException("올바르지 않은 방향입니다.");
    }

    private static Direction calculateDiagonal(final Position source, final Position target) {
        if (source.indexOfRank() < target.indexOfRank()) {
            return calculateTRTL(source, target);
        }
        return calculateDRDL(source, target);
    }

    private static Direction calculateDRDL(final Position source, final Position target) {
        if (source.indexOfFile() < target.indexOfFile()) {
            return Direction.DOWN_RIGHT;
        }
        return Direction.DOWN_LEFT;
    }

    private static Direction calculateTRTL(final Position source, final Position target) {
        if (source.indexOfFile() < target.indexOfFile()) {
            return Direction.TOP_RIGHT;
        }
        return Direction.TOP_LEFT;
    }

    private static Direction calculateLR(final Position source, final Position target) {
        if (source.indexOfFile() > target.indexOfFile()) {
            return Direction.LEFT;
        }
        return Direction.RIGHT;
    }

    private static Direction calculateTD(final Position source, final Position target) {
        if (source.indexOfRank() < target.indexOfRank()) {
            return Direction.TOP;
        }
        return DOWN;
    }

    public static boolean isDiagonal(final Position source, final Position target) {
        Direction direction = of(source, target);
        return Set.of(TOP_RIGHT, TOP_LEFT, DOWN_RIGHT, DOWN_LEFT).contains(direction);
    }

    public static boolean isVertical(final Position source, final Position target) {
        Direction direction = of(source, target);
        return Set.of(TOP, DOWN).contains(direction);
    }
}
