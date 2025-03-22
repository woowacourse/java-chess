package chess;

import java.util.Arrays;

public enum Row {

    EIGHT(7),
    SEVEN(6),
    SIX(5),
    FIVE(4),
    FOUR(3),
    THREE(2),
    TWO(1),
    ONE(0);

    private final int value;

    Row(final int value) {
        this.value = value;
    }

    public static Row parseToRowByNumber(final int number) {
        return Arrays.stream(Row.values())
                .filter(row -> row.value == number)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 위치 : " + number));
    }

    public boolean isTop() {
        return ordinal() == 0;
    }

    public boolean isBottom() {
        return ordinal() + 1 == values().length;
    }

    public boolean canMoveUp(final int step) {
        return ordinal() - step >= 0;
    }

    public Row moveUp() {
        return moveUp(1);
    }

    public Row moveUp(final int step) {
        if (canMoveUp(step)) {
            return values()[ordinal() - step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public boolean canMoveDown(final int step) {
        return ordinal() + step < values().length;
    }

    public Row moveDown() {
        return moveDown(1);
    }

    public Row moveDown(final int step) {
        if (canMoveDown(step)) {
            return values()[ordinal() + step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public int getValue() {
        return value;
    }

    public boolean isAvailableRow() {
        return this.value >= 0 && this.value <= 7;
    }

    @Override
    public String toString() {
        return "Row{" +
                "value=" + value +
                '}';
    }
}
