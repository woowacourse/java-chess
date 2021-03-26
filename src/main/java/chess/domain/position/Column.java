package chess.domain.position;

import chess.exception.PositionException;
import java.util.Arrays;
import java.util.Locale;

public enum Column {
    A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8);

    private final int value;

    Column(int value) {
        this.value = value;
    }

    public static Column from(Character lineName) {
        String upperName = lineName.toString().toUpperCase(Locale.ROOT);

        return Arrays.stream(Column.values())
                .filter(column -> column.name().equals(upperName))
                .findAny()
                .orElseThrow(() -> new PositionException("유효하지 않은 열입니다."));
    }

    public static int differance(Column column1, Column column2) {
        return column1.value - column2.value;
    }

    public Column nextColumn(int moveValue) {
        int targetValue = this.value + moveValue;
        return Arrays.stream(Column.values())
                .filter(column -> column.value == targetValue)
                .findAny()
                .orElseThrow(() -> new PositionException("유효하지 않은 열입니다."));
    }
}
