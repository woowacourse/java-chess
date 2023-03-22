package chessgame.domain.piecetype;

public interface PieceType {

    boolean isReachableByRule(Coordinate startCoordinate, Coordinate endCoordinate);

    boolean canReap();

    boolean isSameTypeWith(PieceTypeSymbol otherType);
}
