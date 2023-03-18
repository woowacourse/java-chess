package domain.piece;

public interface Piece {

    boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate);
    boolean canReap();
    default boolean isPawn() {
        return false;
    }
}
