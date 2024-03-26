package domain.direction;

import java.util.List;

public class Direction {

    private static final int ROW_DIRECTION = 0;
    private static final int COLUMN_DIRECTION = 1;

    private final int getRowDirection;
    private final int columnDirection;

    private Direction(int getRowDirection, int columnDirection) {
        this.getRowDirection = getRowDirection;
        this.columnDirection = columnDirection;
    }

    public static Direction from(List<Integer> direction) {
        return new Direction(direction.get(ROW_DIRECTION), direction.get(COLUMN_DIRECTION));
    }
}
