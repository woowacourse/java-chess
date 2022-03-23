package chess.domain.board;

import java.util.Arrays;

public enum Column {

    FILE_A("a", 1),
    FILE_B("b", 2),
    FILE_C("c", 3),
    FILE_D("d", 4),
    FILE_E("e", 5),
    FILE_F("f", 6),
    FILE_G("g", 7),
    FILE_H("h", 8),
    ;

    private final String value;
    private final int x;

    Column(String value, int x) {
        this.value = value;
        this.x = x;
    }

    public static Column of(String input) {
        return Arrays.stream(Column.values())
                .filter(column -> column.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않는 입력값입니다."));
    }

    public Column move(int x) {
        return findX(this.x + x);
    }

    private Column findX(int input) {
        return Arrays.stream(Column.values())
                .filter(column -> column.x == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않는 입력값입니다."));
    }
}
