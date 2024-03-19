package position;

import java.util.Objects;

public class Column {

    private Position position;

    public Column(int position) {
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
        Column column = (Column) o;
        return Objects.equals(position, column.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
