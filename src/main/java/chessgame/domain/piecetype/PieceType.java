package chessgame.domain.piecetype;

import chessgame.domain.coordinate.Coordinate;

import java.util.Objects;

public abstract class PieceType {

    private final PieceTypeSymbol pieceTypeSymbol;
    private final double score;

    protected PieceType(PieceTypeSymbol pieceTypeSymbol, double score) {
        this.pieceTypeSymbol = pieceTypeSymbol;
        this.score = score;
    }

    public abstract boolean isReachableByRule(final Coordinate startCoordinate,
                                              final Coordinate endCoordinate);

    public abstract boolean canReap();

    public boolean isSameTypeWith(final PieceTypeSymbol otherType) {
        return this.pieceTypeSymbol.equals(otherType);
    }

    public double score() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceType pieceType = (PieceType) o;
        return Double.compare(pieceType.score, score) == 0 && pieceTypeSymbol == pieceType.pieceTypeSymbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceTypeSymbol, score);
    }
}
