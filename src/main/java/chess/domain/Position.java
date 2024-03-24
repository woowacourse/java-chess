package chess.domain;

import java.util.Objects;
import java.util.stream.IntStream;

public class Position {

    private final Row row;
    private final Column column;

    public Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public Position calculateNextPosition(Direction direction, int weight) {
        int rowDistance = direction.calculateRowDistance(weight);
        int columnDistance = direction.calculateColumnDistance(weight);

        Row nextRow = row.calculateNextRow(rowDistance);
        Column nextColumn = column.calculateNextColumn(columnDistance);
        return new Position(nextRow, nextColumn);
    }

    public int calculateMaxDistance(Direction direction, int maxMoveDistance) {
        return IntStream.rangeClosed(1, maxMoveDistance)
                .filter(weight -> isInRange(direction, weight))
                .max()
                .orElse(0);
    }

    private boolean isInRange(Direction direction, int weight) {
        int rowDistance = direction.calculateRowDistance(weight);
        int columnDistance = direction.calculateColumnDistance(weight);

        return row.isNextInRange(rowDistance) && column.isNextInRange(columnDistance);
    }

    public boolean isSameRow(Row row) {
        return this.row == row;
    }

    public int getRowIndex() {
        return row.getIndex();
    }

    public int getColumnIndex() {
        return column.getIndex();
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
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "Position{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
