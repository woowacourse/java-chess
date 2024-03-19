package position;

import java.util.Objects;

public class Row {

    private Position position;

    public Row(int position) {
        this.position = new Position(position);
    }

    public void move(int nextPosition) {
        position.move(nextPosition);
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
