package chess.domain;

import java.util.Objects;

public class Position {
    private final Row row;
    private final Column column;

    private Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(final Row row, final Column column) {
        return new Position(row, column);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return Objects.equals(row, position.row) &&
                Objects.equals(column, position.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
