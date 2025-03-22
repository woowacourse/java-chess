package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Column {

    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    public static Column from(String columnString) {
        return Arrays.stream(Column.values())
                .filter(column -> column.name().equals(columnString))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("column 없습니당"));
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

    public Column move(final int step) {
        return moveRight(step);


    }

    public List<Column> betweenColumns(Column column) {
        List<Column> columnList = new ArrayList<>();

        int start = Math.min(this.ordinal(), column.ordinal());
        int end = Math.max(this.ordinal(), column.ordinal());

        for (int i = start + 1; i < end; i++) {
            columnList.add(Column.values()[i]);
        }

        return columnList;
    }

}
