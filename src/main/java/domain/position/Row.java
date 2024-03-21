package domain.position;

import java.util.Objects;

public class Row {

    private Position position;

    public Row(Position position) {
        this.position = position;
    }

    public Row(int position) {
        this(new Position(position));
    }

    public void moveBy(int distance) {
        position.moveBy(distance);
    }

    public int getRowDifference(Row row) {
        return position.getMinusPosition(row.position);
    }

    public Row copied() {
        return new Row(position.copied());
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
