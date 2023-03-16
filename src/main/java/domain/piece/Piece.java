package domain.piece;

public interface Piece {

    boolean isReachableByRule(Coordinate startCoordinate, Coordinate endCoordinate);
    boolean canReap();
}
