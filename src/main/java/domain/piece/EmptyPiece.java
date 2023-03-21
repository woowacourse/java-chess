package domain.piece;

public class EmptyPiece implements Piece {

    @Override
    public boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        throw new UnsupportedOperationException();
    }
}
