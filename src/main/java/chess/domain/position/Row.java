package chess.domain.position;

import chess.domain.exception.WrongPositionException;

import java.util.Arrays;

public enum Row {
    FIRST("a", 1),
    SECOND("b", 2),
    THIRD("c", 3),
    FOURTH("d", 4),
    FIFTH("e", 5),
    SIXTH("f", 6),
    SEVENTH("g", 7),
    EIGHTH("h", 8);

    private final String name;
    private final int value;

    Row(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static Row of(String name) {
        return Arrays.stream(Row.values())
                .filter(row->row.name.equals(name))
                .findFirst()
                .orElseThrow(WrongPositionException::new);
    }

    public Row calculate(int value) {
        return Arrays.stream(Row.values())
                .filter(row -> row.value == this.value + value)
                .findFirst()
                .orElseThrow(WrongPositionException::new);
    }

    public boolean isSame(Row row) {
        return this.equals(row);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
