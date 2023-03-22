package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;

public interface PieceType {

    boolean isReachableByRule(Coordinate startCoordinate, Coordinate endCoordinate);

    boolean canReap();

    boolean isSameTypeWith(PieceTypeSymbol otherType);
}
