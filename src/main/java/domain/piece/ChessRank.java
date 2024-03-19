package domain.piece;

public enum ChessRank {
    ONE("1", 7),
    TWO("2", 6),
    THREE("3", 5),
    FOUR("4", 4),
    FIVE("5", 3),
    SIX("6", 2),
    SEVEN("7", 1),
    EIGHT("8", 0);

    private final String value;
    private final int index;

    ChessRank(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public static int minIndex() {
        return EIGHT.index;
    }

    public static int maxIndex() {
        return ONE.index;
    }

    public int index() {
        return index;
    }
}
