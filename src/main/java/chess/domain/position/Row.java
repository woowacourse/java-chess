package chess.domain.position;

import chess.domain.position.exception.InvalidRowException;

import java.util.Arrays;

public enum Row {
    EIGHTH("8", 8), SEVENTH("7", 7), SIXTH("6", 6), FIFTH("5", 5),
    FOURTH("4", 4), THIRD("3", 3), SECOND("2", 2), FIRST("1", 1);

    private final String lineName;
    private final int value;

    Row(String lineName, int value) {
        this.lineName = lineName;
        this.value = value;
    }

    public static Row from(Character lineName) {
        return Arrays.stream(Row.values())
                .filter(Row -> Row.lineName.equals(lineName.toString()))
                .findAny()
                .orElseThrow(InvalidRowException::new);
    }

    public String getLineName() {
        return lineName;
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
}
