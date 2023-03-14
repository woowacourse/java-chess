package chess.domain.board;

import java.util.Arrays;

public enum Col {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String column;

    Col(final String column) {
        this.column = column;
    }

    public static Col from(final String column) {
        return Arrays.stream(Col.values())
                .filter(col -> col.column.equals(column))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 column입니다."));
    }
}
