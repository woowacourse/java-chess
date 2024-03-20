package position;

import java.util.Objects;

public class Row {

    private Position position;

    public Row(Position position) {
        this.position = position;
    }

    public Row(int position) {
        this(new Position(position));
    }

    public void move(int nextPosition) {
        position.move(nextPosition);
    }

    public int getPositionValue() {
        return position.getPosition();
    }

    public int getRowMinus(Row row) {
        return position.getMinusPosition(row.position);
    }

    public Row update(int rowDirection) {
        return new Row(position.update(rowDirection));
    }

    public Row copied() {
        return new Row(position.copied());
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
