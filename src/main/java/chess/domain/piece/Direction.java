package chess.domain.piece;

public class Direction {
    private final int column;
    private final int row;

    public Direction(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int column() {
        return column;
    }

    public int row() {
        return row;
    }
}
