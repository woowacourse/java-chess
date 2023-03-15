package domain.piecetype;

public interface PieceType {

    boolean isReachableByRule(Coordinate startCoordinate, Coordinate endCoordinate);
    boolean canReap();
}
