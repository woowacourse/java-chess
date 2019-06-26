package chess.domain;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    N(0, 1, (x, y) -> x == 0 && y > 0),
    NE(1, 1, (x, y) -> x > 0 && y > 0 && isAbsEqual(x, y)),
    E(1, 0, (x, y) -> x > 0 && y == 0),
    SE(1, -1, (x, y) -> x > 0 && y < 0 && isAbsEqual(x, y)),
    S(0, -1, (x, y) -> x == 0 && y < 0),
    SW(-1, -1, (x, y) -> x < 0 && y < 0 && isAbsEqual(x, y)),
    W(-1, 0, (x, y) -> x < 0 && y == 0),
    NW(-1, 1, (x, y) -> x < 0 && y > 0 && isAbsEqual(x, y)),
    KNIGHT(100, 100, (x, y) -> Math.abs(x) + Math.abs(y) == 3);

    private static boolean isAbsEqual(final int x, final int y) {
        return Math.abs(x) == Math.abs(y);
    }

    public static final List<Direction> WHITE_PAWN_DIRECTION = Arrays.asList(N, NE, NW);
    public static final List<Direction> BLACK_PAWN_DIRECTION = Arrays.asList(S, SE, SW);
    public static final List<Direction> DIAGONAL_DIRECTION = Arrays.asList(NE, SE, SW, NW);
    public static final List<Direction> CROSS_DIRECTION = Arrays.asList(N, E, S, W);
    public static final List<Direction> ALL_DIRECTION = Arrays.asList(N, NE, E, SE, S, SW, W, NW);

    private final int x;
    private final int y;
    private final DirectionCheck directionCheck;

    Direction(int x, int y, DirectionCheck directionCheck) {
        this.x = x;
        this.y = y;
        this.directionCheck = directionCheck;
    }

    public static Direction valueOf(int x, int y) {
        for (Direction direction : Direction.values()) {
            if (direction.directionCheck.check(x, y)) {
                return direction;
            }
        }
        return KNIGHT;
    }

    public boolean isDiagonal() {
        return this == NE || this == SE || this == SW || this == NW;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
