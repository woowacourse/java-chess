package chess.model;

import java.util.Arrays;
import java.util.List;

public enum Direction {

    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),
    SOUTHEAST(1, -1),
    NORTHEAST(1, 1),
    SOUTHWEST(-1, -1),
    NORTHWEST(-1, 1),
    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private static final String INVALID_DIRECTION_ERROR_MESSAGE = "유효하지 않은 방향입니다.";

    private final int row;
    private final int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static Direction of(int row, int col) {
        return Arrays.stream(values())
                .filter(direction -> direction.col == col && direction.row == row)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DIRECTION_ERROR_MESSAGE));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public static List<Direction> getNonKnightDirection() {
        return List.of(EAST, WEST, SOUTH, NORTH, SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> getKnightDirection() {
        return List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }
}
