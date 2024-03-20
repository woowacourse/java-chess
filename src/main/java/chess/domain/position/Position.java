package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position {

    public static final List<Position> ALL_POSITIONS = Arrays.stream(Row.values())
            .flatMap(row -> Arrays.stream(Column.values())
                    .map(column -> new Position(row, column)))
            .toList();

    private final Row row;
    private final Column column;

    public Position(Row row, Column column) {
        this.row = Objects.requireNonNull(row);
        this.column = Objects.requireNonNull(column);
    }

    public Position moveToEast() {
        return new Position(this.row, this.column.toEast());
    }

    public Position moveToWest() {
        return new Position(this.row, this.column.toWest());
    }

    public Position moveToNorth() {
        return new Position(this.row.toNorth(), this.column);
    }

    public Position moveToSouth() {
        return new Position(this.row.toSouth(), this.column);
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
