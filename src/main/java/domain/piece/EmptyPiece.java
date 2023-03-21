package domain.piece;

import domain.piece.move.Coordinate;

public class EmptyPiece implements Piece {

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        throw new UnsupportedOperationException();
    }
}
