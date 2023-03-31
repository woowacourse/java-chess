package chess.domain;

import chess.domain.direction.Direction;

import java.util.Arrays;

public enum Column {
    COLUMN_0("8", 0),
    COLUMN_1("7", 1),
    COLUMN_2("6", 2),
    COLUMN_3("5", 3),
    COLUMN_4("4", 4),
    COLUMN_5("3", 5),
    COLUMN_6("2", 6),
    COLUMN_7("1", 7);

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

    public static Column from(final int index) {
        return Arrays.stream(Column.values())
                .filter(column -> column.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Column은 0 ~ 7값만 가능합니다."));
    }

    public Column move(final Direction direction) {
        final int movedColumnIndex = index + direction.getColumnIndex();

        return from(movedColumnIndex);
    }

    public int findDirection(final Column otherColumn) {
        return Integer.compare(otherColumn.index, index);
    }

    public boolean isMovable(final Direction direction) {
        final int movedColumn = index + direction.getColumnIndex();
        return Arrays.stream(Column.values())
                .anyMatch(column -> column.index == movedColumn);
    }

    public int diff(final Column column) {
        return index - column.index;
    }

    public String getType() {
        return type;
    }
}
