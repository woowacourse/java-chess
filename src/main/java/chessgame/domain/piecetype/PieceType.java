package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Camp;

public abstract class PieceType {

    private final PieceTypeSymbol pieceTypeSymbol;
    private final Camp camp;
    private final double score;

    protected PieceType(final PieceTypeSymbol pieceTypeSymbol, final Camp camp, final double score) {
        this.pieceTypeSymbol = pieceTypeSymbol;
        this.camp = camp;
        this.score = score;
    }

    public abstract boolean isReachableByRule(final Coordinate startCoordinate,
                                              final Coordinate endCoordinate);

    public abstract boolean isCatchable(final Camp otherCamp,
                                        final Coordinate startCoordinate,
                                        final Coordinate endCoordinate);


    public abstract boolean canReap();

    public boolean isEmpty() {
        return pieceTypeSymbol.isEmpty();
    }

    public boolean isSameCamp(final Camp camp) {
        return this.camp.equals(camp);
    }

    public boolean isSameTypeWith(final PieceTypeSymbol otherType) {
        return this.pieceTypeSymbol.equals(otherType);
    }

    public Camp camp() {
        return camp;
    }

    public double score() {
        return score;
    }
}
