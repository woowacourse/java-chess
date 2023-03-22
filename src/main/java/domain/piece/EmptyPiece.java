package domain.piece;

import domain.piece.move.Coordinate;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Color.NEUTRAL);
    }

    @Override
    protected boolean isReachableByRuleWhenMovingNotVariates(final Coordinate start, final Coordinate end) {
        throw new UnsupportedOperationException();
    }
}
