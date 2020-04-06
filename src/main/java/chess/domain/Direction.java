package chess.domain;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private int fileDegree;
    private int rankDegree;

    Direction(int fileDegree, int rankDegree) {
        this.fileDegree = fileDegree;
        this.rankDegree = rankDegree;
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, SOUTH, EAST, WEST);
    }

    public static List<Direction> linearAndDiagonalDirection() {
        return Arrays.asList(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST, NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, WWN, WWS, EEN, EES);
    }

    public static List<Direction> blackPawnCaptureDirection() {
        return Arrays.asList(SOUTHWEST, SOUTHEAST);
    }

    public static List<Direction> whitePawnCaptureDirection() {
        return Arrays.asList(NORTHWEST, NORTHEAST);
    }

    public static Direction blackPawnDirection() {
        return SOUTH;
    }

    public static Direction whitePawnDirection() {
        return NORTH;
    }

    public int getFileDegree() {
        return fileDegree;
    }

    public int getRankDegree() {
        return rankDegree;
    }

}