package chess.domain.position;

import java.util.List;

public enum Direction {

    ONE_UP_R(1, 0),
    ONE_DOWN_R(-1, 0),
    ONE_UP_C(0, 1),
    ONE_DOWN_C(0, -1),

    ONE_UP_R_UP_C(1, 1),
    ONE_UP_R_DOWN_C(1, -1),
    ONE_DOWN_R_UP_C(-1, 1),
    ONE_DOWN_R_DOWN_C(-1, -1),

    TWO_UP_R(2, 0),
    TWO_DOWN_R(-2, 0),

    TWO_UP_R_ONE_UP_C(2, 1),
    TWO_UP_R_ONE_DOWN_C(2, -1),
    TWO_DOWN_R_ONE_UP_C(-2, 1),
    TWO_DOWN_R_ONE_DOWN_C(-2, -1),
    ONE_UP_R_TWO_UP_C(1, 2),
    ONE_UP_R_TWO_DOWN_C(1, -2),
    ONE_DOWN_R_TWO_UP_C(-1, 2),
    ONE_DOWN_R_TWO_DOWN_C(-1, -2);

    private final int row;
    private final int column;

    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int moveRow(int row) {
        return this.row + row;
    }

    public int moveColumn(int column) {
        return this.column + column;
    }

    public static List<Direction> knight() {
        return List.of(TWO_UP_R_ONE_UP_C, TWO_UP_R_ONE_DOWN_C, TWO_DOWN_R_ONE_UP_C, TWO_DOWN_R_ONE_DOWN_C,
                ONE_UP_R_TWO_DOWN_C, ONE_DOWN_R_TWO_UP_C, ONE_UP_R_TWO_UP_C, ONE_UP_R_UP_C, ONE_DOWN_R_TWO_DOWN_C);
    }

    public static List<Direction> king() {
        return List.of(ONE_UP_R, ONE_UP_C, ONE_UP_R_UP_C, ONE_DOWN_R, ONE_DOWN_C,
                ONE_UP_R_DOWN_C, ONE_DOWN_R_UP_C, ONE_DOWN_R_DOWN_C);
    }

    public static List<Direction> diagonal() {
        return List.of(ONE_UP_R_UP_C, ONE_UP_R_DOWN_C, ONE_DOWN_R_UP_C, ONE_DOWN_R_DOWN_C);
    }

    public static List<Direction> straightLine() {
        return List.of(ONE_UP_R, ONE_DOWN_R, ONE_UP_C, ONE_DOWN_C);
    }

    public static List<Direction> pawnWhiteDiagonal() {
        return List.of(ONE_DOWN_R_DOWN_C, ONE_DOWN_R_UP_C);
    }

    public static List<Direction> pawnBlackDiagonal() {
        return List.of(ONE_UP_R_DOWN_C, ONE_UP_R_UP_C);
    }

    public static List<Direction> pawnWhiteFirstTurn() {
        return List.of(ONE_DOWN_R, TWO_DOWN_R);
    }

    public static List<Direction> pawnBlackFirstTurn() {
        return List.of(ONE_UP_R, TWO_UP_R);
    }

    public static List<Direction> pawnWhiteTurn() {
        return List.of(ONE_DOWN_R);
    }

    public static List<Direction> pawnBlackTurn() {
        return List.of(ONE_UP_R);
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }
}
