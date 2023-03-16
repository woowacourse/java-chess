package chess.domain.movement;

public enum Direction {
    STRAIGHT,
    DIAGONAL,
    L_SHAPE,
    OTHER;

    public static Direction from(final int fileInterval, final int rankInterval) {
        if (fileInterval == 0 || rankInterval == 0) {
            return STRAIGHT;
        }

        if (Math.abs(fileInterval) == Math.abs(rankInterval)) {
            return DIAGONAL;
        }

        if (Math.pow(fileInterval, 2) + Math.pow(rankInterval, 2) == 5) {
            return L_SHAPE;
        }

        return OTHER;
    }
}
