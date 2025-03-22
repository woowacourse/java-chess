package chess.position;

import java.util.Arrays;

public enum Row {

    EIGHT("8", 0),
    SEVEN("7", 1),
    SIX("6", 2),
    FIVE("5", 3),
    FOUR("4", 4),
    THREE("3", 5),
    TWO("2", 6),
    ONE("1", 7);

    public final String realY;
    public final int y;

    Row(final String realY, final int y) {
        this.realY = realY;
        this.y = y;
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

    public static Row from(final String s) {
        return Arrays.stream(values())
                .filter(value -> value.realY.equals(s))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("입력이상해"));
    }

    public int offset(final Row row) {
        return row.y - this.y;
    }
}
