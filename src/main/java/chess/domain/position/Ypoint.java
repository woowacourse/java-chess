package chess.domain.position;

public enum Ypoint {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int value;

    Ypoint(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
