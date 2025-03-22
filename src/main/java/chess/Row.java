package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Row {

    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1");

    private final String value;

    Row(String value) {
        this.value = value;
    }

    public static Row from(String rowString) {
        return Arrays.stream(Row.values())
                .filter(row -> row.value.equals(rowString))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("row없습니당"));
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

    public Row move(final int step) {

        return moveUp(step);

    }

    public List<Row> betweenRows(Row row) {
        List<Row> rowList = new ArrayList<>();

        int start = Math.min(this.ordinal(), row.ordinal());
        int end = Math.max(this.ordinal(), row.ordinal());

        for (int i = start + 1; i < end; i++) {
            rowList.add(Row.values()[i]);
        }

        return rowList;
    }
}
