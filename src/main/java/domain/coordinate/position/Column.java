package domain.coordinate.position;

import java.util.Objects;

public class Column {

    private final Position position;

    public Column(Position position) {
        this.position = position;
    }

    public Column next(int direction) {
        return new Column(position.next(direction));
    }

    public int getColumnDifference(Column column) {
        return position.getMinusPosition(column.position);
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
