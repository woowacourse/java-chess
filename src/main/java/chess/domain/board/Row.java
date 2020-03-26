package chess.domain.board;

public enum Row implements Comparable<Row> {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int value;

    Row(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return String.valueOf(getValue());
    }
}
