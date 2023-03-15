package domain.piece;

import domain.position.Position;

public final class King extends Piece {

    private static final String NAME = "K";
    private static final int DIAGONAL_DISTANCE = 2;
    private static final int STRAIGHT_DISTANCE = 1;

    public King(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        if (source.isDiagonal(destination) && source.getDistance(destination) == DIAGONAL_DISTANCE) {
            return true;
        }

        return source.isStraight(destination) && source.getDistance(destination) == STRAIGHT_DISTANCE;
    }
}
