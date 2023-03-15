package chess.initial;

public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int rank;

    Rank(final int rank) {
        this.rank = rank;
    }

    public int value() {
        return rank;
    }
}
