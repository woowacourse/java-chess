package domain.piece;

import domain.position.Position;

public final class King extends Piece {

    private static final String NAME = "K";

    public King(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        if (source.isDiagonal(destination) && source.getDistance(destination) == 2) {
            return true;
        }

        return source.isStraight(destination) && source.getDistance(destination) == 1;
    }
}
