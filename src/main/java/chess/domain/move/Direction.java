package chess.domain.move;

public enum Direction {

    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1)
    ;

    private final int vertical;
    private final int horizon;

    Direction(int vertical, int horizon) {
        this.vertical = vertical;
        this.horizon = horizon;
    }
}
