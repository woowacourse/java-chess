package chess.domain.piece.rule;

public enum Distance {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7);

    private int value;

    Distance(final int value){
        this.value = value;
    }

    public boolean isBelow(final Distance other){
        return this.value <= other.value;
    }

    public int getValue() {
        return value;
    }
}
