package chess.domain.piece.property;

public enum Direction {

    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    EAST_NORTHEAST(2, 1),
    EAST_SOUTHEAST(2, -1),
    WEST_NORTHWEST(-2, 1),
    WEST_SOUTHWEST(-2, -1),
    NORTH_NORTHEAST(1, 2),
    NORTH_NORTHWEST(-1, 2),
    SOUTH_SOUTHEAST(1, -2),
    SOUTH_SOUTHWEST(-1, -2),
    SOUTH_SOUTH(0, -2),
    NORTH_NORTH(0, 2);

    private final int xPosition;
    private final int yPosition;

    Direction(final int xPosition, final int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                '}';
    }
}
