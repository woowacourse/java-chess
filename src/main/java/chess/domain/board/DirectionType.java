package chess.domain.board;

import java.util.Arrays;
import java.util.List;

public enum DirectionType {
    EAST(1, 0),
    SOUTHEAST(1, 1),
    WEST(-1, 0),
    NORTHWEST(-1, -1),
    SOUTH(0, 1),
    SOUTHWEST(-1, 1),
    NORTH(0, -1),
    NORTHEAST(1, -1),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private int xDegree;
    private int yDegree;

    DirectionType(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public static DirectionType valueOf(Point prev, Point next) {
        int xDistance = next.xDistance(prev);
        int yDistance = next.yDistance(prev);
        int size = prev.maxAbsoluteValue(next);

        return Arrays.stream(values())
                .filter(value -> value.xDegree == xDistance / size)
                .filter(value -> value.yDegree == yDistance / size)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<DirectionType> getKnightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<DirectionType> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public int getxDegree() {
        return xDegree;
    }

    public int getyDegree() {
        return yDegree;
    }
}
