package chess.model;

public enum Direction {
    NORTH(0, 1),
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1),
    ;

    private int row;
    private int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
