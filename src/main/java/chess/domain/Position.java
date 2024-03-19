package chess.domain;

public class Position {
    private final int x;
    private final int y;

    private Position(int x, int y) {
        validateRange(x, y);
        this.x = x;
        this.y = y;
    }

    public static Position of(String position) {
        int x = position.charAt(0) - 'a' + 1;
        int y = position.charAt(1) - '1' + 1;

        return new Position(x, y);
    }

    public void validateRange(int x, int y) {
        if (isNotInRange(x) || isNotInRange(y)) {
            throw new IllegalArgumentException("보드의 범위를 벗어난 좌표입니다.");
        }
    }

    private boolean isNotInRange(int coordinate) {
        return coordinate < 1 || coordinate > 8;
    }

    public PositionDifference calculateDifference(Position otherPosition) {
        int xDifference = this.x - otherPosition.x;
        int yDifference = this.y - otherPosition.y;

        return new PositionDifference(xDifference, yDifference);
    }
}
