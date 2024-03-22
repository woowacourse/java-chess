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

    // TODO : 리팩터링
    public static Direction of(final Position source, final Position target) {
        if (source.indexOfFile() == target.indexOfFile()) {
            if (source.indexOfRank() < target.indexOfRank()) {
                return Direction.TOP;
            }
            return DOWN;
        }
        if (source.indexOfRank() == target.indexOfRank()) {
            if (source.indexOfFile() > target.indexOfFile()) {
                return Direction.LEFT;
            }
            return Direction.RIGHT;
        }
        if (source.indexOfRank() < target.indexOfRank() && source.calculateFileDistanceTo(target) == source.calculateRankDistanceTo(target)) {
            if (source.indexOfFile() < target.indexOfFile()) {
                return Direction.TOP_RIGHT;
            }
            return Direction.TOP_LEFT;
        }
        if (source.indexOfRank() > target.indexOfRank() && source.calculateFileDistanceTo(target) == source.calculateRankDistanceTo(target)) {
            if (source.indexOfFile() < target.indexOfFile()) {
                return Direction.DOWN_RIGHT;
            }
            return Direction.DOWN_LEFT;
        }
        throw new IllegalArgumentException("올바르지 않은 방향입니다.");
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
