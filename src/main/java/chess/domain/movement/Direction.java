package chess.domain.movement;

public enum Direction {
    STRAIGHT,
    DIAGONAL,
    L_SHAPE,
    OTHER;

    private static final int STRAIGHT_DISTANCE = 0;
    private static final int EXPONENTIATION_NUMBER = 2;
    private static final int L_SHAPE_INCLINATION_DISTANCE = 5;

    public static Direction of(final int fileInterval, final int rankInterval) {
        if (fileInterval == STRAIGHT_DISTANCE || rankInterval == STRAIGHT_DISTANCE) {
            return STRAIGHT;
        }

        if (Math.abs(fileInterval) == Math.abs(rankInterval)) {
            return DIAGONAL;
        }

        if (Math.pow(fileInterval, EXPONENTIATION_NUMBER) + Math.pow(rankInterval, EXPONENTIATION_NUMBER) == L_SHAPE_INCLINATION_DISTANCE) {
            return L_SHAPE;
        }

        return OTHER;
    }
}
