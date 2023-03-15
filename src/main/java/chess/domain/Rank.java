package chess.domain;

public enum Rank {
    EIGHT("8", 0),
    SEVEN("7", 1),
    SIX("6", 2),
    FIVE("5", 3),
    FOUR("4", 4),
    THREE("3", 5),
    TWO("2", 6),
    ONE("1", 7);

    private final String rank;
    private final int index;

    Rank(final String rank, final int index) {
        this.rank = rank;
        this.index = index;
    }
}
