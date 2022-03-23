package chess;

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

    Rank(int index) {
        this.index = index;
    }

    public int absMinus(Rank rank) {
        return Math.abs(index - rank.index);
    }
}
