package chess.model;

import java.util.Objects;

public class Position {

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 7;
    private static final int VALID_COORDINATE_LENGTH = 2;
    private static final char MIN_COLUMN = 'a';
    private static final char MIN_ROW = '1';
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;

    private final int row;
    private final int column;

    public Position(int row, int column) {
        validateRange(row, column);
        this.row = row;
        this.column = column;
    }

    public static Position from(String coordinate) {
        if (coordinate.length() != VALID_COORDINATE_LENGTH) {
            throw new IllegalArgumentException("유효하지 않은 좌표값입니다.");
        }
        int column = coordinate.charAt(COLUMN_INDEX) - MIN_COLUMN;
        int row = MAX_VALUE - coordinate.charAt(ROW_INDEX) + MIN_ROW;
        return new Position(row, column);
    }

    private void validateRange(int row, int column) {
        if (row < MIN_VALUE || row > MAX_VALUE || column < MIN_VALUE || column > MAX_VALUE) {
            throw new IllegalArgumentException("좌표 값은 1이상 8이하의 값만 가능합니다.");
        }
    }

    public Position makeRemotePosition(int rowDifference, int columnDifference) {
        return new Position(this.row + rowDifference, this.column + columnDifference);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
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
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
