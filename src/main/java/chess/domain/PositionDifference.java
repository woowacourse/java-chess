package chess.domain;

public record PositionDifference(int xDifference, int yDifference) {
    public boolean isMagnitudeEqual() {
        return Math.abs(xDifference) == Math.abs(yDifference);
    }
}
