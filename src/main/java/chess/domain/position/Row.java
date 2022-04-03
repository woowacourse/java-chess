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

    Row(int value) {
        this.value = value;
    }

    public static List<Integer> valuesByDescending() {
        return Arrays.stream(values())
                .sorted((o1, o2) -> o2.value - o1.value)
                .map(Row::getValue)
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
            throw new IllegalArgumentException("[ERROR] 행은 숫자로 입력되어야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    public boolean isSame(Row row) {
        return this == row;
    }

    public int gap(Row row) {
        return Math.abs(this.value - row.value);
    }

    public boolean isGreaterThan(Row row) {
        return row.value < this.value;
    }

    public boolean isSmallerThan(Row row) {
        return this.value < row.value;
    }

    public List<Row> rowPaths(Row targetRow) {
        int gap = gap(targetRow);
        if (targetRow.value > this.value) {
            return upperRowsRange(this.value, gap);
        }
        List<Row> rows = upperRowsRange(targetRow.value, gap);
        Collections.reverse(rows);
        return rows;
    }

    private List<Row> upperRowsRange(int startValue, int gap) {
        List<Row> rows = new ArrayList<>();
        for (int i = 1; i < gap; i++) {
            rows.add(find(startValue + i));
        }
        return rows;
    }

    private Row find(int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("'" + value + "'는 올바르지 않은 로우입니다."));
    }

}
