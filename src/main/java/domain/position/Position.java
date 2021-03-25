package domain.position;

import java.util.Objects;

public class Position {

    public static final int CHESS_BOARD_START_RANGE = 0;
    public static final int CHESS_BOARD_END_RANGE = 8;

    private final RowDegree rowDegree;
    private final ColumnDegree columnDegree;

    public Position(String chessCoordinates) {
        this.rowDegree = new RowDegree(chessCoordinates);
        this.columnDegree = new ColumnDegree(chessCoordinates);
    }

    public Position(RowDegree rowDegree, ColumnDegree columnDegree) {
        this.rowDegree = rowDegree;
        this.columnDegree = columnDegree;
    }

    public Position(int rowDegree, int columnDegree) {
        this.rowDegree = new RowDegree(rowDegree);
        this.columnDegree = new ColumnDegree(columnDegree);
    }

    public Position sum(Direction direction) {
        return new Position(direction.rowDegreeSum(rowDegree),
            direction.columnDegreeSum(columnDegree));
    }

    public Position difference(Position target) {
        return new Position(target.rowDegree.difference(rowDegree),
            target.columnDegree.difference(columnDegree));
    }

    public boolean isRowGreaterThanZero() {
        return rowDegree.isGreaterThanZero();
    }

    public boolean isRowLessThanZero() {
        return rowDegree.isLessThanZero();
    }

    public boolean isColumnGreaterThanZero() {
        return columnDegree.isGreaterThanZero();
    }

    public boolean isColumnLessThanZero() {
        return columnDegree.isLessThanZero();
    }

    public boolean isNotLinearPosition(Position target) {
        return !rowDegree.equals(target.rowDegree)
            && !columnDegree.equals(target.columnDegree);
    }

    public boolean isColumnEquals(ColumnDegree columnDegree) {
        return this.columnDegree.equals(columnDegree);
    }

    public boolean isNotDiagonalPosition(Position target) {
        int rowDifference = rowDegree.abs(target.rowDegree);
        int colDifference = columnDegree.abs(target.columnDegree);

        return !(rowDifference != 0 && colDifference != 0) || rowDifference != colDifference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(rowDegree, position.rowDegree) && Objects
            .equals(columnDegree, position.columnDegree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowDegree, columnDegree);
    }

    public boolean isChessBoardPosition() {
        return rowDegree.isChessBoardPosition() && columnDegree.isChessBoardPosition();
    }
}