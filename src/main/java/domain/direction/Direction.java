package domain.direction;

import java.util.List;

public record Direction(int rowDirection, int columnDirection) {

    private static final int ROW_DIRECTION = 0;
    private static final int COLUMN_DIRECTION = 1;

    public static Direction from(List<Integer> direction) {
        return new Direction(direction.get(ROW_DIRECTION), direction.get(COLUMN_DIRECTION));
    }
}
