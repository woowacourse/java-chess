package chess.domain;

public enum Rank {
    EIGHT(7),
    SEVEN(6),
    SIX(5),
    FIVE(4),
    FOUR(3),
    THREE(2),
    TWO(1),
    ONE(0);

    private final int y;

    Rank(final int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
}
