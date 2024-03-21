package chess.view;

import chess.domain.Row;
import java.util.Arrays;

public enum RowMapper {
    RANK1(Row.RANK1, "1"),
    RANK2(Row.RANK2, "2"),
    RANK3(Row.RANK3, "3"),
    RANK4(Row.RANK4, "4"),
    RANK5(Row.RANK5, "5"),
    RANK6(Row.RANK6, "6"),
    RANK7(Row.RANK7, "7"),
    RANK8(Row.RANK8, "8");

    private final Row row;
    private final String value;

    RowMapper(Row row, String value) {
        this.row = row;
        this.value = value;
    }

    public static Row findByInputValue(String value) {
        return Arrays.stream(values())
                .filter(row -> row.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 Column 이 없습니다."))
                .row;
    }
}
