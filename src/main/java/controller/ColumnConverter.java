package controller;

import java.util.Arrays;

public enum ColumnConverter {
    FIRST(1, "a"),
    SECOND(2, "b"),
    THIRD(3, "c"),
    FOURTH(4, "d"),
    FIFTH(5, "e"),
    SIXTH(6, "f"),
    SEVENTH(7, "g"),
    EIGHTH(8, "h"),
    ;

    private final int column;
    private final String sign;

    ColumnConverter(final int column, final String sign) {
        this.column = column;
        this.sign = sign;
    }

    public static int findColumn(final String sign) {
        final ColumnConverter columnConverter = Arrays.stream(ColumnConverter.values())
            .filter(view -> view.sign.equals(sign))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당 위치는 존재하지 않습니다."));
        return columnConverter.column;
    }
}
