package domain.position;

import java.util.Objects;

public class Position {

    private static final int CHESS_BOARD_START_RANGE = 0;
    private static final int CHESS_BOARD_END_RANGE = 8;
    private final int rowDegree;
    private final int columnDegree;

    public Position(String chessCoordinates) {
        this.rowDegree = (chessCoordinates.charAt(1) - '8') * -1;
        this.columnDegree = chessCoordinates.charAt(0) - 'a';
    }

    public Position(int rowDegree, int columnDegree) {
        this.rowDegree = rowDegree;
        this.columnDegree = columnDegree;
    }

    public int getRowDegree() {
        return rowDegree;
    }

    public int getColumnDegree() {
        return columnDegree;
    }

    public Position sum(Direction direction) {
        return new Position(rowDegree + direction.getRowDegree(),
            columnDegree + direction.getColumnDegree());
    }

    public Position diff(Position target) {
        return new Position(target.rowDegree - rowDegree, target.columnDegree - columnDegree);
    }

    public boolean isChessBoardPosition() {
        return rowDegree >= CHESS_BOARD_START_RANGE
            && rowDegree < CHESS_BOARD_END_RANGE
            && columnDegree >= CHESS_BOARD_START_RANGE
            && columnDegree < CHESS_BOARD_END_RANGE;
    }

    public boolean isNotLinearPosition(Position target) {
        return rowDegree != target.rowDegree
            && columnDegree != target.columnDegree;
    }

    public boolean isNotDiagonalPosition(Position target) {
        int rowDiff = Math.abs(rowDegree - target.rowDegree);
        int colDiff = Math.abs(columnDegree - target.columnDegree);

        return !(rowDiff != 0 && colDiff != 0) || rowDiff != colDiff;
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
        return rowDegree == position.rowDegree && columnDegree == position.columnDegree;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowDegree, columnDegree);
    }
}
