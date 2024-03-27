package chess.domain.position;

import java.util.Arrays;
import java.util.List;

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

    public static Direction findDirection(int fileDiff, int rankDiff) {
        int gcd = calculateGCD(fileDiff,rankDiff);
        int fileUnit = calculateUnit(fileDiff,gcd);
        int rankUnit = calculateUnit(rankDiff,gcd);
        return Arrays.stream(values())
                .filter(direction -> direction.x == fileUnit && direction.y == rankUnit)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 방향입니다."));
    }

    private static int calculateUnit(final int distance, final int gcd) {
        return distance / gcd;
    }

    private static int calculateGCD(final int fileDiff, final int rankDiff) {
        int a = Math.max(fileDiff, rankDiff);
        int b = Math.min(fileDiff,rankDiff);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    public boolean isDiagonal() {
        return List.of(Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT).contains(this);
    }

    public boolean isCross() {
        return List.of(Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT).contains(this);
    }

    public boolean isVertical() {
        return List.of(UP, DOWN).contains(this);
    }

    public boolean isHorizontal() {
        return List.of(LEFT, RIGHT).contains(this);
    }

    public boolean isUpSide() {
        return List.of(UP, UP_LEFT, UP_RIGHT).contains(this);
    }

    public boolean isDownSide() {
        return List.of(DOWN, DOWN_LEFT, DOWN_RIGHT).contains(this);
    }

    public boolean isLeftSide() {
        return List.of(LEFT, UP_LEFT, DOWN_LEFT).contains(this);
    }
}
