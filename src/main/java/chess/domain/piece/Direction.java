package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1),
    LEFT_UP(-1, 1),
    LEFT_DOWN(-1, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1),
    LEFT_LEFT_UP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1),
    LEFT_UP_UP(-1, 2),
    LEFT_DOWN_DOWN(-1, -2),
    RIGHT_RIGHT_UP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1),
    RIGHT_UP_UP(1, 2),
    RIGHT_DOWN_DOWN(1, -2);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction findDirection(int fileDiff, int rankDiff) {
        if (fileDiff == 0 && rankDiff == 0) {
            throw new IllegalArgumentException("현재 위치와 도착 위치가 동일합니다.");
        }
        if (fileDiff == 0 || rankDiff == 0) {
            return findVerticalOrHorizontalDirection(fileDiff, rankDiff);
        }
        if (Math.abs(fileDiff) == Math.abs(rankDiff)) {
            return findDiagonalDirection(fileDiff, rankDiff);
        }
        return findKnightDirection(fileDiff, rankDiff);
    }

    private static Direction findVerticalOrHorizontalDirection(int fileDiff, int rankDiff) {
        if (fileDiff > 0) {
            return RIGHT;
        }
        if (fileDiff < 0) {
            return LEFT;
        }
        if (rankDiff > 0) {
            return UP;
        }
        return DOWN;
    }

    private static Direction findDiagonalDirection(int fileDiff, int rankDiff) {
        if (fileDiff > 0 && rankDiff > 0) {
            return RIGHT_UP;
        }
        if (fileDiff > 0 && rankDiff < 0) {
            return RIGHT_DOWN;
        }
        if (fileDiff < 0 && rankDiff < 0) {
            return LEFT_DOWN;
        }
        return LEFT_UP;
    }

    private static Direction findKnightDirection(int fileDiff, int rankDiff) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.hasSameDegree(fileDiff, rankDiff))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("주어진 위치로의 방향을 찾을 수 없습니다."));
    }

    public static List<Direction> getKingDirections() {
        return Arrays.asList(LEFT, RIGHT, UP, DOWN, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
    }

    public static List<Direction> getQueenDirections() {
        return Arrays.asList(LEFT, RIGHT, UP, DOWN, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
    }

    public static List<Direction> getKnightDirections() {
        return Arrays.asList(LEFT_LEFT_DOWN, LEFT_LEFT_UP, LEFT_UP_UP, LEFT_DOWN_DOWN, RIGHT_DOWN_DOWN, RIGHT_UP_UP, RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN);
    }

    public static List<Direction> getBishopDirections() {
        return Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN);
    }

    public static List<Direction> getRookDirections() {
        return Arrays.asList(LEFT, RIGHT, UP, DOWN);
    }

    public static List<Direction> getPawnDirections(TeamType teamType) {
        if (teamType == TeamType.BLACK) {
            return Arrays.asList(DOWN, LEFT_DOWN, RIGHT_DOWN);
        }
        return Arrays.asList(UP, LEFT_UP, RIGHT_UP);
    }

    private boolean hasSameDegree(int fileDiff, int rankDiff) {
        return x == fileDiff && y == rankDiff;
    }

    public boolean isDiagonal() {
        return Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN).contains(this);
    }

    public int calculateRank(int y) {
        return this.y + y;
    }

    public int calculateFile(int x) {
        return this.x + x;
    }
}
