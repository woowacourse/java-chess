package chess.domain.movement;

public enum Direction {
    STRAIGHT,
    DIAGONAL,
    L_SHAPE,
    OTHER;

    private static final int STRAIGHT_LIMIT = 0;
    private static final int EXPONENT = 2;
    private static final int EXPONENTIATION = 5;

    public static Direction from(final int fileInterval, final int rankInterval) {
        if (fileInterval == STRAIGHT_LIMIT || rankInterval == STRAIGHT_LIMIT) {
            return STRAIGHT;
        }

        if (Math.abs(fileInterval) == Math.abs(rankInterval)) {
            return DIAGONAL;
        }

        if (Math.pow(fileInterval, EXPONENT) + Math.pow(rankInterval, EXPONENT) == EXPONENTIATION) {
            return L_SHAPE;
        }

        return OTHER;
    }
}
