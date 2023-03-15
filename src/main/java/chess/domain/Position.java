package chess.domain;

import java.util.Objects;

public class Position implements Comparable<Position> {
    private final int row;
    private final int column;

    public Position(final int row, final int column) {
        validate(row, column);
        this.row = row;
        this.column = column;
    }

    private void validate(final int row, final int column) {
        if (row < 0) {
            throw new IllegalArgumentException("가로 위치는 0 미만일 수 없습니다.");
        }
        if (column < 0) {
            throw new IllegalArgumentException("세로 위치는 0 미만일 수 없습니다.");
        }
    }

    @Override
    public int compareTo(final Position position) {
        final int columnCompare = Integer.compare(this.row, position.row);
        if (columnCompare == 0) {
            return Integer.compare(this.column, position.column);
        }
        return columnCompare;
    }

    @Override
    public String toString() {
        return "(" + row + "," + column + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
