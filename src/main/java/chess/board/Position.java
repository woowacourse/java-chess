package chess.board;

import java.util.Objects;

public class Position {

    public static final int MIN_ROW_INDEX = 1;
    public static final int MAX_ROW_INDEX = 8;
    public static final int MIN_COLUMN_INDEX = 1;
    public static final int MAX_COLUMN_INDEX = 8;

    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    private void validateRowRange(int row) {
        if (!isRowInRange(row)) {
            throw new IllegalArgumentException(row + ": 행의 범위를 벗어났습니다.");
        }
    }

    private boolean isRowInRange(int row) {
        return MIN_ROW_INDEX <= row && row <= MAX_ROW_INDEX;
    }

    private void validateColumnRange(int column) {
        if (!isColumnInRange(column)) {
            throw new IllegalArgumentException(column + ": 열의 범위를 벗어났습니다.");
        }
    }

    private boolean isColumnInRange(int column) {
        return MIN_COLUMN_INDEX <= column && column <= MAX_COLUMN_INDEX;
    }

    public boolean canMoveByDirection(Direction direction) {
        int nextRow = row + direction.deltaRow();
        int nextColumn = column + direction.deltaColumn();
        return isRowInRange(nextRow) && isColumnInRange(nextColumn);
    }

    public Position moveByDirection(Direction direction) {
        int nextRow = row + direction.deltaRow();
        int nextColumn = column + direction.deltaColumn();
        return new Position(nextRow, nextColumn);
    }

    public boolean canMoveByDirections(Movement movement) {
        Position currentPosition = this;
        for (Direction direction : movement.path()) {
            if (!canMoveByDirection(direction)) {
                return false;
            }
            currentPosition = currentPosition.moveByDirection(direction);
        }
        return true;
    }

    public Position moveByDirections(Movement movement) {
        Position currentPosition = this;
        for (Direction direction : movement.path()) {
            if (!canMoveByDirection(direction)) {
                throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
            }
            currentPosition = currentPosition.moveByDirection(direction);
        }
        return currentPosition;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Position position)) {
            return false;
        }
        return Objects.equals(row, position.row) && Objects.equals(column, position.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
