package chess.domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Direction {
    N(0, 1),
    NE(1, 1),
    E(1, 0),
    SE(1, -1),
    S(0, -1),
    SW(-1, -1),
    W(-1, 0),
    NW(-1, 1),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private final int xDegree;
    private final int yDegree;

    Direction(final int xDegree, final int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public int getXDegree() {
        return xDegree;
    }

    public int getYDegree() {
        return yDegree;
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(N, E, S, W);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NE, SE, SW, NW);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(N, E, S, W, NE, SE, SW, NW);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(N, NE, NW);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(S, SE, SW);
    }

    public static List<Direction> whitePawnLinearDirection() {
        return Collections.singletonList(N);
    }

    public static List<Direction> blackPawnLinearDirection() {
        return Collections.singletonList(S);
    }
}
