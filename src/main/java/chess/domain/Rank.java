package chess.domain;

public enum Rank {
    ONE("1", 7),
    TWO("2", 6),
    THREE("3", 5),
    FOUR("4", 4),
    FIVE("5", 3),
    SIX("6", 2),
    SEVEN("7", 1),
    EIGHT("8", 0);

    private final String rank;
    private final int index;

    Rank(String rank, int index) {
        this.rank = rank;
        this.index = index;
    }
}
