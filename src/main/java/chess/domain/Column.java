package chess.domain;

import java.util.Arrays;

public enum Column {
    COLUMN_8("8", 0),
    COLUMN_7("7", 1),
    COLUMN_6("6", 2),
    COLUMN_5("5", 3),
    COLUMN_4("4", 4),
    COLUMN_3("3", 5),
    COLUMN_2("2", 6),
    COLUMN_1("1", 7);

    private final String type;
    private final int index;

    Column(final String type, final int index) {
        this.type = type;
        this.index = index;
    }

    public static Column from(final String inputType) {
        return Arrays.stream(Column.values())
                .filter(columnType -> columnType.type.equals(inputType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 세로 입력값 입니다."));
    }

    public Column move(final int inputIndex) {
        return Arrays.stream(Column.values())
                .filter(columnType -> columnType.index == this.index + inputIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가로 입력값 입니다."));
    }

    public int distance(Column column) {
        return Math.abs(index - column.index);
    }

    public int direction(Column column) {
        return Integer.compare(this.index, column.index);
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }
}
