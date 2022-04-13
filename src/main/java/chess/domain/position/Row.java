package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum Row {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Row(final int value) {
        this.value = value;
    }

    public static List<Integer> valuesByDescending() {
        return Arrays.stream(values())
                .sorted((o1, o2) -> o2.value - o1.value)
                .map(Row::value)
                .collect(Collectors.toList());
    }

    public static Row of(final String value) {
        int rowValue = convertValue(value);
        return Arrays.stream(values())
                .filter(it -> it.value == rowValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("'" + value + "'는 올바르지 않은 로우입니다."));
    }

    private static int convertValue(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("헹은 숫자로 입력되어야 합니다.");
        }
    }

    public static Row findRow(int value) {
        return Arrays.stream(values())
                .filter(row -> row.value == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isSame(final Row row) {
        return this == row;
    }

    public int gap(final Row row) {
        return Math.abs(this.value - row.value);
    }

    public boolean isGreaterThan(final Row row) {
        return row.value < this.value;
    }

    public boolean isSmallerThan(final Row row) {
        return this.value < row.value;
    }

    public List<Row> rowPaths(final Row targetRow) {
        int gap = gap(targetRow);
        if (targetRow.value > this.value) {
            return upperRowsRange(this.value, gap);
        }
        List<Row> rows = upperRowsRange(targetRow.value, gap);
        Collections.reverse(rows);
        return rows;
    }

    private List<Row> upperRowsRange(final int startValue, final int gap) {
        List<Row> rows = new ArrayList<>();
        for (int i = 1; i < gap; i++) {
            rows.add(find(startValue + i));
        }
        return rows;
    }

    private Row find(final int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("'" + value + "'는 올바르지 않은 로우입니다."));
    }

    public int value() {
        return value;
    }
}
