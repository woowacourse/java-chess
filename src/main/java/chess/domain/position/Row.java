package chess.domain.position;

import chess.exception.PositionException;
import java.util.Arrays;

public enum Row {
    EIGHTH(8), SEVENTH(7), SIXTH(6), FIFTH(5),
    FOURTH(4), THIRD(3), SECOND(2), FIRST(1);

    private final String lineName;
    private final int value;

    Row(int value) {
        this.value = value;
        this.lineName = Integer.toString(value);
    }

    public static Row from(Character lineName) {
        return Arrays.stream(Row.values())
                .filter(row -> row.lineName.equals(lineName.toString()))
                .findAny()
                .orElseThrow(() -> new PositionException("유효하지 않은 행입니다."));
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
                .orElseThrow(() -> new PositionException("유효하지 않은 행입니다."));
    }
}
