package chess.model;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int coordinate;

    Rank(int coordinate) {
        this.coordinate = coordinate;
    }

    public int minus(Rank other) {
        return this.coordinate - other.coordinate;
    }

    public boolean isPawnInitialRank(Side side) {
        if (side.isWhite()) {
            return TWO.equals(this);
        }
        return SEVEN.equals(this);
    }

    public int getCoordinate() {
        return coordinate;
    }
}
