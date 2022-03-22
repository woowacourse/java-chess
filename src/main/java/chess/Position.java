package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position  {

    private final Column column;
    private final Row row;

    public Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static List<Position> init() {
        List<Position> positions = new ArrayList<>();

        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                positions.add(new Position(column, row));
            }
        }
        return positions;
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
}
