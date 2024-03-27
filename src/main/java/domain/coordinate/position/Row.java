package domain.coordinate.position;

import java.util.Objects;

public class Row {

    private final Position position;

    public Row(Position position) {
        this.position = position;
    }

    public Row next(int direction) {
        return new Row(position.next(direction));
    }

    public int getRowDifference(Row row) {
        return position.getMinusPosition(row.position);
    }

    public boolean isSamePosition(int otherPosition) {
        return position.isSame(otherPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Row row = (Row) o;
        return Objects.equals(position, row.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
