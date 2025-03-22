package chess.domain.piece;

public enum Row {

    EIGHT,
    SEVEN,
    SIX,
    FIVE,
    FOUR,
    THREE,
    TWO,
    ONE;

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

    public static Row findRowByNumber(String input) {

        if (input.equals("8")) {
            return EIGHT;
        }
        if (input.equals("7")) {
            return SEVEN;
        }
        if (input.equals("6")) {
            return SIX;
        }
        if (input.equals("5")) {
            return FIVE;
        }
        if (input.equals("4")) {
            return FOUR;
        }
        if (input.equals("3")) {
            return THREE;
        }
        if (input.equals("2")) {
            return TWO;
        }
        if (input.equals("1")) {
            return ONE;
        }
        throw new IllegalArgumentException("위치가 올바르지 않습니다.");
    }
}
