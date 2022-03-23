package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {

    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),
    NNE(1, 2),
    NNW(-1, 2),
    WWN(-2, 1),
    WWS(-2, -1),
    EEN(2, 1),
    EES(2, -1),
    SSW(-1, -2),
    SSE(1, -2);

    private final int row;
    private final int column;

    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Direction of(int row, int column) {
        return Arrays.stream(Direction.values())
                .filter(it -> it.row == row && it.column == column)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 컬럼입니다."));
    }

    public static List<Direction> pullKnightDirections() {
        return Arrays.asList(NNE, NNW, WWN, WWS, EEN, EES, SSW, SSE);
    }

    public static List<Direction> pullDiagonalDirections() {
        return Arrays.asList(NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    }

    public static List<Direction> pullStraightDirections() {
        return Arrays.asList(NORTH, SOUTH, EAST, WEST);
    }

    public static List<Direction> pullAllBasicDirections() {
        List<Direction> list = new ArrayList<>(pullDiagonalDirections());
        list.addAll(pullStraightDirections());
        return list;
    }

    public static List<Direction> pullBlackPawnDirections() {
        return Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST);
    }

    public static List<Direction> pullWhitePawnDirections() {
        return Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST);
    }
}