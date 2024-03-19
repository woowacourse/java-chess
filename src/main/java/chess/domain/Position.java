package chess.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Position {
    private static final int MIN_ROW = 1;
    private static final int MAX_ROW = 8;
    private static final int MIN_COLUMN = 1;
    private static final int MAX_COLUMN = 8;

    private static final List<Position> positions;

    static {
        positions = IntStream.rangeClosed(MIN_COLUMN, MAX_COLUMN)
                .boxed()
                .flatMap(column -> IntStream.rangeClosed(MIN_ROW, MAX_ROW)
                        .boxed()
                        .map(row -> new Position(row, column)))
                .toList();
    }

    private final int row;
    private final int column;

    private Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(int row, int column) {
        validateRange(row, column);
        return positions.stream()
                .filter(position -> position.row == row && position.column == column)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("캐시에 해당 값이 존재하지 않습니다."));
    }

    private static void validateRange(int row, int column) {
        if (isRowOutOfRange(row)) {
            throw new IllegalArgumentException(
                    "가로는 %d부터 %d 사이의 값이어야 합니다: %d".formatted(MIN_ROW, MAX_ROW, row));
        }
        if (isColumnOutOfRange(column)) {
            throw new IllegalArgumentException(
                    "세로는 %d부터 %d 사이의 값이어야 합니다: %d".formatted(MIN_COLUMN, MAX_COLUMN, column));
        }
    }

    public static boolean isColumnOutOfRange(int column) {
        return column < MIN_COLUMN || column > MAX_COLUMN;
    }

    public static boolean isRowOutOfRange(int row) {
        return row < MIN_ROW || row > MAX_ROW;
    }

    public int differenceRow(Position newPosition) {
        return newPosition.row - row;
    }

    public int differenceColumn(Position newPosition) {
        return newPosition.column - column;
    }

    public boolean isSameRow(int row) {
        return this.row == row;
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
