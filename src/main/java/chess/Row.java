package chess;

import java.util.Arrays;

// Row에 들어가는 거 숫자임~
public enum Row {

    EIGHT(8), SEVEN(7), SIX(6), FIVE(5), FOUR(4), THREE(3), TWO(2), ONE(1);

    private final int index;

    Row(int index) {
        this.index = index;

    }

    // 문자열 이상한 거 주면 터짐
    public static Row getRow(String row) {
        return getRow(row.toUpperCase().charAt(0));
    }

    // 정확한 범위 내 문자를 주지 않으면 예외 발생
    public static Row getRow(char row) {
        int indexBaseByOne = row - '1' + 1;
        Row result = getRowByIndex(indexBaseByOne);
        return result;
    }

    // 얘는 1베이스로 입력받을까? 인간 친화적으로
    public static Row getRow(int indexBaseByOne) {
        Row result = getRowByIndex(indexBaseByOne);
        return result;
    }

    private static Row getRowByIndex(int indexBaseByOne) {
        return Arrays.stream(values()).filter(row -> row.index == indexBaseByOne).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Row index should be in range 1 to 8"));
    }

    public int getIndex() {
        return this.index;
    }


    // 지금은 미리 구현된 이것들 다 안 쓸 것 같은데?
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
