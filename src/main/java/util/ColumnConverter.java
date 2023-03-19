package util;

import java.util.Arrays;

public enum ColumnConverter {
    FIRST(0, "a"),
    SECOND(1, "b"),
    THIRD(2, "c"),
    FOURTH(3, "d"),
    FIFTH(4, "e"),
    SIXTH(5, "f"),
    SEVENTH(6, "g"),
    EIGHTH(7, "h");

    private final int column;
    private final String sign;

    ColumnConverter(final int column, final String sign) {
        this.column = column;
        this.sign = sign;
    }

    private static int convert(final ColumnConverter findColumn) {
        return findColumn.column;
    }

    public static int findColumn(final String sign) {
        final ColumnConverter findColumn = Arrays.stream(values())
            .filter(view -> view.sign.equals(sign))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("컬럼 인덱스로 변환할 수 있는 값이 아닙니다."));
        return convert(findColumn);
    }
}
