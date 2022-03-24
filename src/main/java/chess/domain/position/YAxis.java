package chess.domain.position;

public enum YAxis {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    YAxis(int value) {
        this.value = value;
    }

    int subtract(YAxis other) {
        return this.value - other.value;
    }
}
