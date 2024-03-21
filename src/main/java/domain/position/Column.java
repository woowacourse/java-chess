package domain.position;

import java.util.Objects;

public class Column {

    private Position position;

    public Column(Position position) {
        this.position = position;
    }

    public Column(int position) {
        this(new Position(position));
    }

    public void moveBy(int distance) {
        position.moveBy(distance);
    }

    public int getColumnDifference(Column column) {
        return position.getMinusPosition(column.position);
    }

    public Column copied() {
        return new Column(position.copied());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Column column = (Column) o;
        return Objects.equals(position, column.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
