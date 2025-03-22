package chess;

import java.util.Arrays;

public enum Row {

    EIGHT, // 0
    SEVEN,
    SIX,
    FIVE,
    FOUR,
    THREE,
    TWO,
    ONE;

    public static Row fromNumber(int number) {
        return Arrays.stream(Row.values())
                .filter(row -> row.ordinal() == 8 - number)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public static String ofOrdinal(int ordinal) {
        return Arrays.stream(Row.values())
                .filter(row -> row.ordinal() == ordinal)
                .map(row -> ((8 - row.ordinal()) + ""))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
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
}
