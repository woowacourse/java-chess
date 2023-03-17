package domain.piece;

public interface Piece {

    boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate);
    boolean canReap();
    boolean isSameTypeWith(final PieceType otherType);
}
