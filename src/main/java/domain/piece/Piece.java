package domain.piece;

public interface Piece {

    boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate);

    default boolean canReap() {
        return false;
    }

    default boolean isPawn() {
        return false;
    }

    default boolean isKing() {
        return false;
    }
}
