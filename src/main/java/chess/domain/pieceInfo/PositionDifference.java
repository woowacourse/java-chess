package chess.domain.pieceInfo;

public record PositionDifference(int xDifference, int yDifference) {
    public boolean isMagnitudeEqual() {
        return Math.abs(xDifference) == Math.abs(yDifference);
    }

    public boolean isOnAxis() {
        return xDifference == 0 || yDifference == 0;
    }

    public boolean isWithinUnitRange() {
        if (!(isMagnitudeEqual() || isOnAxis())) {
            return false;
        }
        return Math.abs(xDifference) <= 1 && Math.abs(yDifference) <= 1;
    }

    public boolean isWithinVerticalRange(final int minimum, final int maximum) {
        return (minimum <= yDifference) && (yDifference <= maximum);
    }
}
