package domain.piece;

public class King {
    private final int row;
    private final int column;

    public King(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isMovable(int targetRow, int targetColumn) {
        return Math.abs(row - targetRow) <= 1 && Math.abs(column - targetColumn) <= 1;
    }
}
