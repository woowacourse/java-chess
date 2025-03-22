package chess;

import java.util.Arrays;

public enum Column {

    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    public static Column parseChar(final char target) {
        return Arrays.stream(Column.values())
                .filter(column -> column.name().charAt(0) == target)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("맞지 않은 문자입니다"));
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

    public Column move(final int step) {
        if (step < 0) {
            return moveLeft(step);
        }
        return moveRight(step);
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

    public int calculateColumnDistance(Column column) {
        return ordinal() - column.ordinal();
    }
}
