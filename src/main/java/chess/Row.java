package chess;

import java.util.InputMismatchException;

public enum Row {

    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int value;

    Row(int value) {
        this.value = value;
    }

    public static Row getRow(String value) {
        try {
            int intValue = Integer.parseInt(value);
            return getRow(intValue);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 Row입니다.");
        }
    }

    public static Row getRow(int value) {
        for (Row row : values()) {
            if (row.value == value) {
                return row;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 Row입니다.");
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
