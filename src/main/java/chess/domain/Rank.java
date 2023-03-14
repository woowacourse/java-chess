package chess.domain;

public enum Rank {

    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8");

    private final int position;
    private final String symbol;

    Rank(final int position, final String symbol) {
        this.position = position;
        this.symbol = symbol;
    }

    public int distanceTo(final Rank other) {
        return Math.abs(position - other.position);
    }

    public int getPosition() {
        return position;
    }
}
