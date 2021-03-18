package chess.domain.board;

public enum Direction {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1),
    LEFT_UP(-1, 1),
    LEFT_DOWN(-1, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction findDirection(int fileDiff, int rankDiff) {
        if (fileDiff == 0 && rankDiff == 0) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        if (fileDiff == 0) {
            return verticalDirection(rankDiff);
        }
        if (rankDiff == 0) {
            return horizontalDirection(fileDiff);
        }
        return diagonalDirection(fileDiff, rankDiff);
    }

    private static Direction diagonalDirection(int fileDiff, int rankDiff) {
        if (Math.abs(fileDiff) != Math.abs(rankDiff)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
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

    private static Direction verticalDirection(int rankDiff) {
        if (rankDiff > 0) {
            return UP;
        }
        return DOWN;
    }

    private static Direction horizontalDirection(int fileDiff) {
        if (fileDiff > 0) {
            return RIGHT;
        }
        return LEFT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
