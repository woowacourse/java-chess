package chess.domain.piece.strategy;

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

    L_UU(-1, 2),
    R_UU(1, 2),
    RR_U(2, 1),
    RR_D(2, -1),
    R_DD(1, -2),
    L_DD(-1, -2),
    LL_U(-2, 1),
    LL_D(-2, -1);

//    private final List<Integer> coordinates;
    private final int columnNumber;
    private final int rowNumber;

//    Direction(List<Integer> coordinates) {
//        this.coordinates = coordinates;
//    }
    Direction(int columnNumber, int rowNumber) {
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
    }

    public List<Integer> getCoordinates() {
        return Arrays.asList(columnNumber, rowNumber);
    }
}
