package domain.piece;

public class Bishop {
    private final int row;
    private final int column;

    public Bishop(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isMovable(int targetRow, int targetColumn) {
        return Math.abs(this.row - targetRow) == Math.abs(this.column - targetColumn);
    }
}
