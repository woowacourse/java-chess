package chess.webview;

import java.util.Arrays;

public enum ColumnName {
    ONE(1, "a"),
    TWO(2, "b"),
    THREE(3, "c"),
    FOUR(4, "d"),
    FIVE(5, "e"),
    SIX(6, "f"),
    SEVEN(7, "g"),
    EIGHT(8, "h");

    private static final String UNVALID_COLUMN_EXCEPTION = "[ERROR] 유효하지 않은 column입니다.";
    private final int index;
    private final String name;

    ColumnName(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static String of(int column) {
        return Arrays.stream(ColumnName.values())
                .filter(it -> it.index == column)
                .map(it -> it.name)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNVALID_COLUMN_EXCEPTION));
    }
}
