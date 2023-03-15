package chess.domain;

public enum Rank {
    ONE("1", 0),
    TWO("2", 1),
    THREE("3", 2),
    FOUR("4", 3),
    FIVE("5", 4),
    SIX("6", 5),
    SEVEN("7", 6),
    EIGHT("8", 7);

    private final String value;
    private final int index;

    Rank(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public int calculateDistance(final Rank otherRank) {
        return Math.abs(this.index - otherRank.index);
    }
}
