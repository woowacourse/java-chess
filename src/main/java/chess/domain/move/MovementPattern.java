package chess.domain.move;

import java.util.Arrays;

public enum MovementPattern {

    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    NE(1, 1),
    NW(-1, 1),
    SE(1, -1),
    SW(-1, -1),
    NNW(-1, 2),
    NNE(1, 2),
    EEN(2, 1),
    EES(2, -1),
    SSE(1, -2),
    SSW(-1, -2),
    WWS(-2, -1),
    WWN(-2, 1),
    START_MOVEMENT_OF_BLACK_PAWN(0, -2),
    START_MOVEMENT_OF_WHITE_PAWN(0, 2);

    private final int horizon;
    private final int vertical;

    MovementPattern(int horizon, int vertical) {
        this.horizon = horizon;
        this.vertical = vertical;
    }

    public static MovementPattern of(final int horizon, final int vertical) {
        return Arrays.stream(values())
                .filter(value -> value.horizon == horizon && value.vertical == vertical)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[Error] 존재하지 않는 이동 전략입니다."));
    }
}
