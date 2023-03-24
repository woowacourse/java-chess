package chess.domain;

import chess.domain.direction.Direction;

import java.util.Arrays;

public enum Row {
    ROW_0("a", 0),
    ROW_1("b", 1),
    ROW_2("c", 2),
    ROW_3("d", 3),
    ROW_4("e", 4),
    ROW_5("f", 5),
    ROW_6("g", 6),
    ROW_7("h", 7);

    private final String type;
    private final int index;

    Row(final String type, final int index) {
        this.type = type;
        this.index = index;
    }

    public static Row from(final String inputType) {
        return Arrays.stream(Row.values())
                .filter(rowType -> rowType.type.equals(inputType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가로 입력값 입니다."));
    }

    public static Row from(final int index) {
        return Arrays.stream(Row.values())
                .filter(row -> row.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Row는 0 ~ 7 값만 존재합니다."));
    }

    public Row move(final Direction direction) {
        final int movedIndex = index + direction.getRowIndex();
        return findBy(movedIndex);
    }

    private Row findBy(final int rowSum) {
        return Arrays.stream(Row.values())
                .filter(row -> row.index == rowSum)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("범위를 벗어난 Row 값 입니다."));
    }

    public int findDirection(final Row otherRow) {
        return Integer.compare(otherRow.index, index);
    }

    public boolean isMovable(final Direction direction) {
        final int movedRow = index + direction.getRowIndex();
        return Arrays.stream(Row.values())
                .anyMatch(row -> row.index == movedRow);
    }

    public int diff(final Row row) {
        return index - row.index;
    }
}
