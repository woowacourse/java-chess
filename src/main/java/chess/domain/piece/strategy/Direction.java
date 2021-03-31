package chess.domain.piece.strategy;

import java.util.Arrays;
import java.util.List;

public enum Direction {

    UP(0, 1),
    RIGHT(1, 0),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1),
    LEFT_DOWN(-1, -1),
    LEFT_UP(-1, 1),

    UU(0, 2),
    DD(0, -2),

    L_UU(-1, 2),
    R_UU(1, 2),
    RR_U(2, 1),
    RR_D(2, -1),
    R_DD(1, -2),
    L_DD(-1, -2),
    LL_U(-2, 1),
    LL_D(-2, -1);

    private final int columnValue;
    private final int rowValue;

    Direction(final int columnValue, final int rowValue) {
        this.columnValue = columnValue;
        this.rowValue = rowValue;
    }

    public static List<Direction> straightDirection() {
        return Arrays.asList(UP, RIGHT, DOWN, LEFT);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(RIGHT_UP, RIGHT_DOWN, LEFT_DOWN, LEFT_UP);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(UP, RIGHT, DOWN, LEFT, RIGHT_UP, RIGHT_DOWN, LEFT_DOWN, LEFT_UP);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(L_UU, R_UU, RR_U, RR_D, R_DD, L_DD, LL_U, LL_D);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(UP, RIGHT_UP, LEFT_UP, UU);
    }

    public static List<Direction> movedWhitePawnDirection() {
        return Arrays.asList(UP, RIGHT_UP, LEFT_UP);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(DOWN, RIGHT_DOWN, LEFT_DOWN, DD);
    }

    public static List<Direction> movedBlackPawnDirection() {
        return Arrays.asList(DOWN, RIGHT_DOWN, LEFT_DOWN);
    }

    public int columnValue() {
        return columnValue;
    }

    public int rowValue() {
        return rowValue;
    }
}
