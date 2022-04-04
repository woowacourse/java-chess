package chess.webview;

import java.util.Arrays;

public enum RowName {
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8");

    private static final String UNVALID_ROW_EXCEPTION = "[ERROR] 유효하지 않은 row입니다.";
    private final int index;
    private final String name;

    RowName(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static String of(int row) {
        return Arrays.stream(RowName.values())
                .filter(it -> it.index == row)
                .map(it -> it.name)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNVALID_ROW_EXCEPTION));
    }
}
