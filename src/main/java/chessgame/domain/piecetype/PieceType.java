package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;

public abstract class PieceType {

    private final PieceTypeSymbol pieceTypeSymbol;

    protected PieceType(PieceTypeSymbol pieceTypeSymbol) {
        this.pieceTypeSymbol = pieceTypeSymbol;
    }

    public abstract boolean isReachableByRule(final Coordinate startCoordinate,
                                              final Coordinate endCoordinate);

    public abstract boolean canReap();

    public boolean isSameTypeWith(final PieceTypeSymbol otherType) {
        return this.pieceTypeSymbol.equals(otherType);
    }
}
