package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1);

    private final int x;
    private final int y;

    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static List<Direction> all() {
        return List.of(values());
    }

    public static List<Direction> diagonal() {
        return List.of(Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
    }

    public static List<Direction> cross() {
        return List.of(Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT);
    }

    public static Direction findDirection(int fileDiff, int rankDiff) {
        if (fileDiff != 0 && rankDiff != 0 && Math.abs(fileDiff) != Math.abs(rankDiff)) {
            throw new IllegalArgumentException("올바르지 않은 방향입니다.");
        }
        return Arrays.stream(values())
                .filter(direction -> direction.x == Math.signum(fileDiff))
                .filter(direction -> direction.y == Math.signum(rankDiff))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 방향입니다."));
    }

    public boolean isDiagonal() {
        return diagonal().contains(this);
    }

    public boolean isVertical() {
        return Set.of(UP, DOWN).contains(this);
    }
}
