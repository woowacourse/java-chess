package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {

    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),

    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),

    SSE(1, -2),
    SSW(-1, -2),
    NNE(1, 2),
    NNW(-1, 2),
    EES(2, -1),
    EEN(2, 1),
    WWS(-2, -1),
    WWN(-2, 1);


    private final int columnValue;
    private final int rowValue;

    Direction(final int columnValue, final int rowValue) {
        this.columnValue = columnValue;
        this.rowValue = rowValue;
    }

    public List<Direction> getDiagonal() {
        final List<Direction> directions = new ArrayList<>();
        if (this == NORTH || this == SOUTH) {
            directions.add(getDirectionByValues(-1, rowValue));
            directions.add(getDirectionByValues(1, rowValue));
            return directions;
        }
        if (this == EAST || this == WEST) {
            directions.add(getDirectionByValues(columnValue, -1));
            directions.add(getDirectionByValues(columnValue, 1));
            return directions;
        }
        throw new IllegalStateException("해당 디렉션의 대각선을 구할 수 없습니다");
    }

    public static Direction getDirectionByValues(final int columnValue, final int rowValue) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.columnValue == columnValue && direction.rowValue == rowValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 Direction이 없습니다."));
    }

    public int getColumnValue() {
        return columnValue;
    }

    public int getRowValue() {
        return rowValue;
    }
}
