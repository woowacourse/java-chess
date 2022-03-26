package chess;

import java.util.List;

public enum Direction {

    EAST(0, 1),
    WEST(0, -1),
    SOUTH(-1, 0),
    NORTH(1, 0),
    NORTHEAST(1, 1),
    NORTHWEST(1, -1),
    SOUTHWEST(-1, -1),
    SOUTHEAST(-1, 1);

    private final int row;
    private final int column;

    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isMovablePosition(Rank rank, File file) {
        return rank.canAdd(row) && file.canAdd(column);
    }

    public Position createMovablePosition(Rank rank, File file) {
        return new Position(rank.add(row), file.add(column));
    }
}
