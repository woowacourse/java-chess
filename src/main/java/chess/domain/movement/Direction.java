package chess.domain.movement;

import static java.lang.Math.*;

public enum Direction {
    STRAIGHT,
    DIAGONAL,
    L_SHAPE,
    OTHER;

    private static final int STRAIGHT_LIMIT = 0;
    private static final int EXPONENT = 2;
    private static final int EXPONENTIATION = 5;

    public static Direction of(final int fileInterval, final int rankInterval) {
        if (isStraight(fileInterval, rankInterval)) {
            return STRAIGHT;
        }
        if (isDiagonal(fileInterval, rankInterval)) {
            return DIAGONAL;
        }
        if (isLShape(fileInterval, rankInterval)) {
            return L_SHAPE;
        }
        return OTHER;
    }

    private static boolean isLShape(final int fileInterval, final int rankInterval) {
        return pow(fileInterval, EXPONENT) + pow(rankInterval, EXPONENT) == EXPONENTIATION;
    }

    private static boolean isDiagonal(final int fileInterval, final int rankInterval) {
        return abs(fileInterval) == abs(rankInterval);
    }

    private static boolean isStraight(final int fileInterval, final int rankInterval) {
        return fileInterval == STRAIGHT_LIMIT || rankInterval == STRAIGHT_LIMIT;
    }
}
