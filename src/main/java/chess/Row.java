package chess;

public enum Row {

    EIGHT,
    SEVEN,
    SIX,
    FIVE,
    FOUR,
    THREE,
    TWO,
    ONE;

    public static Row change(char value) {
        if (value == '1') {
            return ONE;
        }

        if (value == '2') {
            return TWO;
        }

        if (value == '3') {
            return THREE;
        }

        if (value == '4') {
            return FOUR;
        }

        if (value == '5') {
            return FIVE;
        }

        if (value == '6') {
            return SIX;
        }

        if (value == '7') {
            return SEVEN;
        }

        if (value == '8') {
            return EIGHT;
        }

        throw new IllegalArgumentException("[ERROR] 그런 값은 없어요.");
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
