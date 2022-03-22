package chess.domain.board;

import java.util.Arrays;

public enum Column {

    FILE_A("a"),
    FILE_B("b"),
    FILE_C("c"),
    FILE_D("d"),
    FILE_E("e"),
    FILE_F("f"),
    FILE_G("g"),
    FILE_H("h"),
    ;

    private final String value;

    Column(String value) {
        this.value = value;
    }

    public static Column of(String input) {
        return Arrays.stream(Column.values())
                .filter(column -> column.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않는 입력값입니다."));
    }
}
