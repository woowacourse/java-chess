package chess.domain.position;

import chess.domain.position.exception.InvalidRowException;

import java.util.Arrays;

public enum Row {
    EIGHTH(8), SEVENTH(7), SIXTH(6), FIFTH(5),
    FOURTH(4), THIRD(3), SECOND(2), FIRST(1);

    private final int value;

    Row(int value) {
        this.value = value;
    }

    public static Row from(int value) {
        return Arrays.stream(Row.values())
                .filter(row -> row.value == value)
                .findAny()
                .orElseThrow(InvalidRowException::new);
    }

    public String getValue() {
        return Integer.toString(value);
    }

    public static int differance(Row row1, Row row2) {
        return row1.value - row2.value;
    }

    public Row nextRow(int moveValue) {
        int targetValue = this.value + moveValue;
        return Arrays.stream(Row.values())
                .filter(row -> row.value == targetValue)
                .findAny()
                .orElseThrow(InvalidRowException::new);
    }

    public String row() {
        return Integer.toString(this.value);
    }
}
