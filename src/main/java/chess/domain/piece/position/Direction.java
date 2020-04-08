package chess.domain.piece.position;

public enum Direction {
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),
    STAY(0, 0);

    private final int horizontal;
    private final int vertical;

    Direction(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    static Direction calculate(Position from, Position to) {
        if (from.equals(to)) {
            return STAY;
        }

        if ((from.getX() == to.getX()) && (from.getY() < to.getY())) {
            return NORTH;
        }

        if ((from.getX() == to.getX()) && (to.getY() < from.getY())) {
            return SOUTH;
        }

        if ((from.getX() < to.getX()) && (to.getY() == from.getY())) {
            return EAST;
        }

        if ((to.getX() < from.getX()) && (to.getY() == from.getY())) {
            return WEST;
        }

        if ((from.getX() < to.getX()) && (from.getY() < to.getY())) {
            return NORTH_EAST;
        }

        if ((to.getX()) < from.getX() && (from.getY() < to.getY())) {
            return NORTH_WEST;
        }

        if ((from.getX() < to.getX()) && (to.getY() < from.getY())) {
            return SOUTH_EAST;
        }

        if ((to.getX() < from.getX()) && (to.getY() < from.getY())) {
            return SOUTH_WEST;
        }

        throw new IllegalArgumentException(String.format("%s에서 %s로의 방향을 알 수 없습니다.", from, to));
    }

    int getHorizontal() {
        return horizontal;
    }

    int getVertical() {
        return vertical;
    }

    boolean isPerpendicular() {
        return isVertical() || isHorizontal();
    }

    boolean isNotPerpendicular() {
        return !isVertical() && !isHorizontal();
    }

    boolean isVertical() {
        return this == NORTH || this == SOUTH;
    }

    boolean isDiagonal() {
        return this == NORTH_WEST
                || this == NORTH_EAST
                || this == SOUTH_WEST
                || this == SOUTH_EAST;
    }

    private boolean isHorizontal() {
        return this == EAST || this == WEST;
    }
}
