package chess;

import java.util.Arrays;

public enum Column {

    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private final int value;

    Column(final int value) {
        this.value = value;
    }

    public static Column parseToColumnByNumber(final int number) {
        return Arrays.stream(Column.values())
                .filter(column -> column.value == number)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 위치 : " + number));
    }

    public boolean isFarLeft() {
        return ordinal() == 0;
    }

    public boolean isFarRight() {
        return ordinal() + 1 == values().length;
    }

    public boolean canMoveLeft(final int step) {
        return ordinal() - step >= 0;
    }

    public Column moveLeft() {
        return moveLeft(1);
    }

    public Column moveLeft(final int step) {
        if (canMoveLeft(step)) {
            return values()[ordinal() - step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public boolean canMoveRight(final int step) {
        return ordinal() + step < values().length;
    }

    public Column moveRight() {
        return moveRight(1);
    }

    public Column moveRight(final int step) {
        if (canMoveRight(step)) {
            return values()[ordinal() + step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public boolean isAvailableColumn() {
        return this.value >= 0 && this.value <= 7;
    }

    @Override
    public String toString() {
        return "Column{" +
                "value=" + value +
                '}';
    }
}
