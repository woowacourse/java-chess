package chess;

public class King {

    private static final int UPPER_BOUND_OF_MOVABLE_DISTANCE = 1;

    boolean isMovable(final Position startPosition, final Position endPosition) {
        int diffFile = endPosition.calculateFileDistance(startPosition);
        int diffRank = endPosition.calculateRankDistance(startPosition);

        return Math.abs(diffFile) <= UPPER_BOUND_OF_MOVABLE_DISTANCE
                && Math.abs(diffRank) <= UPPER_BOUND_OF_MOVABLE_DISTANCE;
    }

}
