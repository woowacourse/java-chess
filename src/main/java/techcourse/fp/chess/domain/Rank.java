package techcourse.fp.chess.domain;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int order;

    Rank(final int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
