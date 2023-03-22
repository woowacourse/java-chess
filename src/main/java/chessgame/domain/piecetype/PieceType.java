package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;

public interface PieceType {

    boolean isReachableByRule(final Coordinate startCoordinate, final Coordinate endCoordinate);

    boolean canReap();

    boolean isSameTypeWith(final PieceTypeSymbol otherType);
}
