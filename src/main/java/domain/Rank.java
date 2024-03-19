package domain;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public int gap(final Rank other) {
        return Math.abs(this.index - other.index);
    }
}
