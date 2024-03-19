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
