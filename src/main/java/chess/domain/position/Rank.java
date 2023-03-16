package chess.domain.position;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1),
    ;

    private final int order;

    Rank(int order) {
        this.order = order;
    }

    public int subtractOrder(Rank other) {
        return this.order - other.order;
    }
}
