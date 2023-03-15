package chess;

public class King extends Piece {

    private static final int UPPER_BOUND_OF_MOVABLE_DISTANCE = 1;

    public King(Team team) {
        super(team);
    }


    @Override
    public boolean canMove(final Position startPosition, final Position endPosition) {
        if (startPosition.equals(endPosition)) {
            return false;
        }
        int diffFile = endPosition.calculateFileDistance(startPosition);
        int diffRank = endPosition.calculateRankDistance(startPosition);

        return Math.abs(diffFile) <= UPPER_BOUND_OF_MOVABLE_DISTANCE
                && Math.abs(diffRank) <= UPPER_BOUND_OF_MOVABLE_DISTANCE;
    }

    @Override
    boolean canAttack(final Position startPosition, final Position endPosition) {
        return canMove(startPosition, endPosition);
    }
}
