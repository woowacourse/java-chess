package chess.domain;

import java.util.ArrayList;
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

    public boolean isDifferentColumn(ChessBoardPosition targetPosition) {
        return column != targetPosition.column;
    }

    public List<ChessBoardPosition> createPathPositions(ChessBoardPosition other) {
        List<Integer> rows = ascendingRange(this.row, other.getRow());
        List<Integer> columns = ascendingRange(this.getColumn(), other.getColumn());
        List<ChessBoardPosition> pathPositions = new ArrayList<>();
        for (int i = 0; i < rows.size(); ++i) {
            pathPositions.add(new ChessBoardPosition((char) (columns.get(i).intValue()), rows.get(i)));
        }
        return pathPositions;
    }

    private List<Integer> ascendingRange(int source, int target) {
        if (source > target) {
            return rangeClosed(target, source);
        }
        return rangeClosed(source, target);
    }

    private List<Integer> rangeClosed(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }
}
