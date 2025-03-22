package chess.domain;

import java.util.Arrays;

public enum Column {

    A(1, 'a'),
    B(2, 'b'),
    C(3, 'c'),
    D(4, 'd'),
    E(5, 'e'),
    F(6, 'f'),
    G(7, 'g'),
    H(8, 'h');

    private final int value;
    private final char charValue;

    Column(int value, char charValue) {
        this.value = value;
        this.charValue = charValue;
    }

    public static Column from(int value) {
        return Arrays.stream(Column.values())
                .filter(col -> col.value == value)
                .findAny()
                .orElseThrow();
    }

    public static Column from(char charValue) {
        return Arrays.stream(Column.values())
                .filter(col -> col.charValue == charValue)
                .findAny()
                .orElseThrow();
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

    public int intValue() {
        return value;
    };

}
