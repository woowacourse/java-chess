package chess.domain;

import java.util.Objects;

public class ChessBoardPosition {
    private static final char MINIMUM_COLUMN = 'a';
    private static final char MAXIMUM_COLUMN = 'h';
    private static final int MINIMUM_ROW = 1;
    private static final int MAXIMUM_ROW = 8;
    private static final int COLUMN_INDEX = 0;
    private static final int ROW_INDEX = 1;
    private static final String INVALID_POSITION = "[ERROR] 체스판 위치 입력 형식에 맞지 않습니다.";
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
        try {
            return Character.getNumericValue(positionInput.charAt(ROW_INDEX));
        } catch (Exception e) {
            throw new IllegalArgumentException(INVALID_POSITION);
        }
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

    public ChessBoardPosition move(int columnChange, int rowChange) {
        return new ChessBoardPosition((char)(this.column + columnChange), this.row + rowChange);
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
