package domain.direction;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Direction {

    UP(1, 0),
    DOWN(-1, 0),
    LEFT(0, 1),
    RIGHT(0, -1),

    UP_RIGHT_DIAGONAL(1, 1),
    UP_LEFT_DIAGONAL(1, -1),
    DOWN_LEFT_DIAGONAL(-1, -1),
    DOWN_RIGHT_DIAGONAL(-1, 1),

    UP_UP_RIGHT_DIAGONAL(2, 1),
    UP_UP_LEFT_DIAGONAL(2, -1),
    LEFT_LEFT_UP_DIAGONAL(1, -2),
    LEFT_LEFT_DOWN_DIAGONAL(1, -2),
    RIGHT_RIGHT_UP_DIAGONAL(-1, -2),
    RIGHT_RIGHT_DOWN_DIAGONAL(-1, -2),
    DOWN_DOWN_RIGHT_DIAGONAL(-2, 1),
    DOWN_DOWN_LEFT_DIAGONAL(-2, -1);

    private final int rowDirection;
    private final int columnDirection;

    Direction(int rowDirection, int columnDirection) {
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public static final Set<Direction> STRAIGHT_DIRECTION = Set.of(UP, DOWN, LEFT, RIGHT);
    public static final Set<Direction> DIAGONAL_DIRECTION = Set.of(UP_RIGHT_DIAGONAL, UP_LEFT_DIAGONAL,
            DOWN_LEFT_DIAGONAL, DOWN_RIGHT_DIAGONAL);
    public static final Set<Direction> KNIGHT_DIRECTION = Set.of(UP_UP_RIGHT_DIAGONAL, UP_UP_LEFT_DIAGONAL,
            LEFT_LEFT_UP_DIAGONAL, LEFT_LEFT_DOWN_DIAGONAL, RIGHT_RIGHT_UP_DIAGONAL, RIGHT_RIGHT_DOWN_DIAGONAL,
            DOWN_DOWN_LEFT_DIAGONAL, DOWN_DOWN_RIGHT_DIAGONAL);
    public static final Set<Direction> ALL_DIRECTION = Stream
            .concat(STRAIGHT_DIRECTION.stream(), DIAGONAL_DIRECTION.stream())
            .collect(Collectors.toSet());
    public static final Set<Direction> BLACK_PAWN_DIRECTION = Set.of(DOWN, DOWN_LEFT_DIAGONAL, DOWN_RIGHT_DIAGONAL);
    public static final Set<Direction> WHITE_PAWN_DIRECTION = Set.of(UP, UP_LEFT_DIAGONAL, UP_RIGHT_DIAGONAL);

    public int getRowDirection() {
        return rowDirection;
    }

    public int getColumnDirection() {
        return columnDirection;
    }

    public boolean isEqualDirection(int rowDirection, int columnDirection) {
        return this.rowDirection == rowDirection && this.columnDirection == columnDirection;
    }
}
