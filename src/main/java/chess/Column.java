package chess;

// Column에 들어가는 거 문자열임~
public enum Column {

    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    // 문자열 이상한 거 주면 터짐
    public static Column getColumn(String col) {
        return getColumn(col.toUpperCase().charAt(0));
    }

    // 정확한 범위 내 문자를 주지 않으면 예외 발생
    public static Column getColumn(char col) {
        int index = col - 'A';
        validateIndexIsInColumnRange(index);
        Column result = Column.values()[index];
        return result;
    }

    // 얘는 1베이스로 입력받을까? 인간 친화적으로
    public static Column getColumn(int indexBaseByOne) {
        indexBaseByOne -= 1;
        validateIndexIsInColumnRange(indexBaseByOne);
        Column result = Column.values()[indexBaseByOne];
        return result;
    }

    private static void validateIndexIsInColumnRange(int index) {
        if (index < 0 || index >= 8) {
            throw new IllegalArgumentException("Column index should be in range 0 to 7");
        }
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

    public int getIndex() {
        return this.ordinal() + 1;
    }
}
