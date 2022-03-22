package chess;

import java.util.Arrays;

public enum Row {

    RANK_8("8"),
    RANK_7("7"),
    RANK_6("6"),
    RANK_5("5"),
    RANK_4("4"),
    RANK_3("3"),
    RANK_2("2"),
    RANK_1("1"),
    ;

    private final String value;

    Row(String value) {
        this.value = value;
    }

    public static Row of(String input) {
        return Arrays.stream(Row.values())
                .filter(row -> row.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않는 입력값입니다."));
    }
}
