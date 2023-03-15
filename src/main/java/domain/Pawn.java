package domain;

public interface Pawn extends NewPieceType {

    boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate);
}
