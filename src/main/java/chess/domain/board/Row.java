package chess.domain.board;

import java.util.Arrays;

public enum Row {

    RANK_8("8", 8),
    RANK_7("7", 7),
    RANK_6("6", 6),
    RANK_5("5", 5),
    RANK_4("4", 4),
    RANK_3("3", 3),
    RANK_2("2", 2),
    RANK_1("1", 1),
    ;

    private final String value;
    private final int y;

    Row(String value, int y) {
        this.value = value;
        this.y = y;
    }

    public static Row of(String input) {
        return Arrays.stream(Row.values())
                .filter(row -> row.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않는 입력값입니다."));
    }

    public Row move(int y) {
        return findY(this.y + y);
    }

    private Row findY(int input) {
        return Arrays.stream(Row.values())
                .filter(row -> row.y == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않는 입력값입니다."));
    }
}
