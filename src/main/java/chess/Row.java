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

    // 문자열 이상한 거 주면 터짐
    public static Row getRow(String row) {
        return getRow(row.toUpperCase().charAt(0));
    }

    // 정확한 범위 내 문자를 주지 않으면 예외 발생
    public static Row getRow(char row) {
        int index = row - '1';
        validateIndexIsInRowRange(index);
        Row result = Row.values()[index];
        return result;
    }

    private static void validateIndexIsInRowRange(int index) {
        if (index < 0 || index >= 8) {
            throw new IllegalArgumentException("Row index should be in range 0 to 7");
        }
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
