package point;

import java.util.Objects;

public class Point { // TODO Position 네이밍 고려

    //TODO : 64개 캐싱

    private final Row row;
    private final Column column;

    public Point(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Point point)) {
            return false;
        }
        return Objects.equals(row, point.row) && Objects.equals(column, point.column);
    }
}
