package chess.domain.board;

public enum Direction {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction findDirection(int fileDiff, int rankDiff) {
        if (fileDiff == rankDiff) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        if (fileDiff == 0) {
            return verticalDirection(rankDiff);
        }
        return horizontalDirection(fileDiff);
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
