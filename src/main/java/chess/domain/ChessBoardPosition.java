package chess.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoardPosition {
    private static final char MINIMUM_COLUMN = 'a';
    private static final char MAXIMUM_COLUMN = 'h';
    private static final int MINIMUM_ROW = 1;
    private static final int MAXIMUM_ROW = 8;
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final String POSITION_OUT_OF_RANGE = "[ERROR] 체스판 범위를 벗어나는 위치가 입력되었습니다.";

    private final char column;
    private final int row;

    public ChessBoardPosition(char column, int row) {
        validateRange(column, row);
        this.column = column;
        this.row = row;
    }

    public static ChessBoardPosition of(String positionInput) {
        char column = extractColumn(positionInput);
        int row = extractRow(positionInput);
        return new ChessBoardPosition(column, row);
    }

    private static char extractColumn(String positionInput) {
        return positionInput.charAt(COLUMN_INDEX);
    }

    private static int extractRow(String positionInput) {
        return Character.getNumericValue(positionInput.charAt(ROW_INDEX));
    }

    private void validateRange(char column, int row) {
        validateColumn(column);
        validateRow(row);
    }

    private void validateColumn(char column) {
        if (column < MINIMUM_COLUMN || MAXIMUM_COLUMN < column) {
            throw new IllegalArgumentException(POSITION_OUT_OF_RANGE);
        }
    }

    private void validateRow(int row) {
        if (row < MINIMUM_ROW || MAXIMUM_ROW < row) {
            throw new IllegalArgumentException(POSITION_OUT_OF_RANGE);
        }
    }

    public boolean isSameRow(int row) {
        return this.row == row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isDifferentColumn(ChessBoardPosition targetPosition) {
        return column != targetPosition.column;
    }

    public List<Integer> ascendingRowRange(ChessBoardPosition other) {
        if (row > other.row) {
            return range(other.row, row);
        }
        return range(row, other.row);
    }

    public List<Integer> ascendingColumnRange(ChessBoardPosition other) {
        if (column > other.column) {
            return range(other.column, column);
        }
        return range(column, other.column);
    }

    private List<Integer> range(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }

    public ChessBoardPosition move(int columnChange, int rowChange) {
        return new ChessBoardPosition((char)(this.column + columnChange), this.row + rowChange);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoardPosition that = (ChessBoardPosition) o;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
