package domain.piece;

public interface Pawn extends Piece {

    boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate);
}
