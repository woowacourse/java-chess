package chess.domain.position;

import java.util.Arrays;
import java.util.List;

public enum ChessDirection {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_RIGHT(1, 1),
    UP_LEFT(-1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_DOWN_RIGHT(1, -2),
    LEFT_LEFT_DOWN(-2, -1),
    LEFT_LEFT_UP(-2, 1),
    UP_UP_LEFT(-1, 2),
    UP_UP_RIGHT(1, 2),
    RIGHT_RIGHT_UP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1);

    private final int x;
    private final int y;

    ChessDirection(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static ChessDirection findDirection(int fileDiff, int rankDiff) {
        if (isDiagonalDirection(fileDiff, rankDiff) || isCrossDirection(fileDiff, rankDiff)) {
            fileDiff = (int) Math.signum(fileDiff);
            rankDiff = (int) Math.signum(rankDiff);
        }
        final int x = fileDiff;
        final int y = rankDiff;
        return Arrays.stream(values())
                .filter(direction -> direction.x == x && direction.y == y)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 방향입니다."));
    }

    private static boolean isDiagonalDirection(final int fileDiff, final int rankDiff) {
        return Math.abs(fileDiff) == Math.abs(rankDiff);
    }

    private static boolean isCrossDirection(final int fileDiff, final int rankDiff) {
        return isVerticalDirection(fileDiff, rankDiff) || isHorizontalDirection(fileDiff, rankDiff);
    }

    private static boolean isVerticalDirection(final int fileDiff, final int rankDiff) {
        return rankDiff != 0 && fileDiff == 0;
    }

    private static boolean isHorizontalDirection(final int fileDiff, final int rankDiff) {
        return rankDiff == 0 && fileDiff != 0;
    }

    public boolean isLShaped() {
        return List.of(UP_UP_LEFT, UP_UP_RIGHT, RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN, LEFT_LEFT_DOWN, LEFT_LEFT_UP, DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT).contains(this);
    }

    public boolean isDiagonal() {
        return List.of(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT).contains(this);
    }

    public boolean isCross() {
        return List.of(UP, DOWN, RIGHT, LEFT).contains(this);
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
