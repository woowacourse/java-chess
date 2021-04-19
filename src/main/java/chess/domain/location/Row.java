package chess.domain.location;

public enum Row {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private final String value;
    private static final Row[] rows = values();

    Row(String value) {
        this.value = value;
    }

    public Row move(int value) {
        if ((this.ordinal() + value) < 0 || this.ordinal() + value >= rows.length) {
            throw new IllegalArgumentException("범위를 넘어가는 move 입니다");
        }
        return rows[(this.ordinal() + value)];
    }

    public String value() {
        return value;
    }

    public boolean canMove(int value) {
        int movedIndex = this.ordinal() + value;
        return movedIndex >= 0 && movedIndex < rows.length;
    }
}
