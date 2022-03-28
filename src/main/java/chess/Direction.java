package chess;

public enum Direction {

    EAST(0, 1),
    WEST(0, -1),
    SOUTH(-1, 0),
    NORTH(1, 0),
    NORTHEAST(1, 1),
    NORTHWEST(1, -1),
    SOUTHWEST(-1, -1),
    SOUTHEAST(-1, 1),

    EEN(1, 2),
    EES(-1, 2),
    WWN(1, -2),
    WWS(-1, -2),
    SSE(-2, 1),
    SSW(-2, -1),
    NNE(2, 1),
    NNW(2, -1),

    SS(-2, 0),
    NN(2, 0),
    ;

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
        return Position.of(rank.add(row), file.add(column));
    }
}
