package domain.piece.info;

public enum Rank {
    ONE(0),
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7);

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }
}
