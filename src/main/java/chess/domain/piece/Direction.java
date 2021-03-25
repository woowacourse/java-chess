package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    UP(0, 1),
    RIGHT(1, 0),
    DOWN(0, -1),
    LEFT(-1, 0),
    UP_RIGHT(1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),
    UP_LEFT(-1, 1),

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

    private final int columnNumber;
    private final int rowNumber;

    Direction(int columnNumber, int rowNumber) {
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
    }

    public static List<Direction> straightDirection() {
        return Arrays.asList(UP, RIGHT, DOWN, LEFT);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(UP, RIGHT, DOWN, LEFT, UP_RIGHT, DOWN_RIGHT, DOWN_LEFT, UP_LEFT);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(L_UU, R_UU, RR_U, RR_D, R_DD, L_DD, LL_U, LL_D);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(UP, UP_RIGHT, UP_LEFT, UU);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(DOWN, DOWN_RIGHT, DOWN_LEFT, DD);
    }

    public static List<Direction> downDirection() {
        return Arrays.asList(DOWN, DOWN_LEFT, DOWN_RIGHT, RR_D, LL_D);
    }

    public static List<Direction> upDirection() {
        return Arrays.asList(UP, UP_LEFT, UP_RIGHT, RR_U, LL_U);
    }

    public static List<Direction> rightDirection() {
        return Arrays.asList(R_DD, R_UU, RIGHT, DOWN_RIGHT, UP_RIGHT);
    }

    public static List<Direction> leftDirection() {
        return Arrays.asList(L_DD, L_UU, LEFT, DOWN_LEFT, UP_LEFT);
    }

    public int columnValue(){
        return columnNumber;
    }

    public int rowValue(){
        return rowNumber;
    }
}
