package chess.view;

import chess.domain.Column;
import java.util.Arrays;

public enum ColumnMapper {
    A(Column.A, "a"),
    B(Column.B, "b"),
    C(Column.C, "c"),
    D(Column.D, "d"),
    E(Column.E, "e"),
    F(Column.F, "f"),
    G(Column.G, "g"),
    H(Column.H, "h");

    private final Column column;
    private final String value;

    ColumnMapper(Column column, String value) {
        this.column = column;
        this.value = value;
    }

    public static Column findByInputValue(String value) {
        return Arrays.stream(values())
                .filter(column -> column.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 Column 이 없습니다."))
                .column;
    }
}
