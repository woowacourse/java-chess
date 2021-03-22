package chess.domain.position;

import chess.exception.InvalidColumnException;
import java.util.Arrays;
import java.util.Locale;

public enum Column {
    A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8);

    private final int value;

    Column(int value) {
        this.value = value;
    }

    public static Column from(Character alpha) {
        String upperAlpha = alpha.toString().toUpperCase(Locale.ROOT);

        return Arrays.stream(Column.values())
                .filter(column -> column.name().equals(upperAlpha))
                .findAny()
                .orElseThrow(InvalidColumnException::new);
    }

    public static int differance(Column column1, Column column2) {
        return column1.value - column2.value;
    }

    public Column nextColumn(int moveValue) {
        int targetValue = this.value + moveValue;
        return Arrays.stream(Column.values())
                .filter(column -> column.value == targetValue)
                .findAny()
                .orElseThrow(InvalidColumnException::new);
    }
}
