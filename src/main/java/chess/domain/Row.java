package chess.domain;

import java.util.Arrays;

public enum Row {

    EIGHT('8'),
    SEVEN('7'),
    SIX('6'),
    FIVE('5'),
    FOUR('4'),
    THREE('3'),
    TWO('2'),
    ONE('1');

    private final char number;

    Row(final char number) {
        this.number = number;
    }

    public static Row parseChar(final char target) {
        return Arrays.stream(Row.values())
                .filter(row -> row.getNumber() == target)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("맞지 않은 문자입니다"));
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

    public Row move(final int step) {
        if (step < 0) {
            return moveDown(step);
        }
        return moveUp(step);
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

    public int calculateRowDistance(Row row) {
        return ordinal() - row.ordinal();
    }

    public char getNumber() {
        return number;
    }
}
