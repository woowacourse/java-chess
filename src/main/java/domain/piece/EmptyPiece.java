package domain.piece;

import domain.piece.move.Coordinate;

public class EmptyPiece extends Piece {

    @Override
    protected boolean isReachableByRuleWhenMovingNotVariates(final Coordinate start, final Coordinate end) {
        throw new UnsupportedOperationException();
    }
}
