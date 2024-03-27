package domain.direction;

import java.util.List;
import java.util.Objects;

public class Direction {

    private static final int ROW_DIRECTION = 0;
    private static final int COLUMN_DIRECTION = 1;

    private final int rowDirection;
    private final int columnDirection;

    private Direction(int rowDirection, int columnDirection) {
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public static Direction from(List<Integer> direction) {
        return new Direction(direction.get(ROW_DIRECTION), direction.get(COLUMN_DIRECTION));
    }

    public int getRowDirection() {
        return rowDirection;
    }

    public int getColumnDirection() {
        return columnDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Direction direction = (Direction) o;
        return rowDirection == direction.rowDirection && columnDirection == direction.columnDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowDirection, columnDirection);
    }
}
