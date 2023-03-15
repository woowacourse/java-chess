package domain.piecetype;

public interface Pawn extends PieceType {

    boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate);
}
