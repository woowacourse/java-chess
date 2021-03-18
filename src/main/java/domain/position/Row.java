package domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public enum Row {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private final String value;
    private static Row[] rows = values();

    Row(String value) {
        this.value = value;
    }

    public Row moveBy(int value) {
        if ((this.ordinal() + value) < 0 || this.ordinal() + value >= rows.length) {
            throw new IllegalArgumentException("범위를 넘어가는 move 입니다");
        }
        return rows[(this.ordinal() + value)];
    }

    public String value() {
        return value;
    }

    public List<Row> getBetween(Row to) {
        int start = Math.min(this.ordinal(), to.ordinal());
        int end = Math.max(this.ordinal(), to.ordinal());
        List<Row> betweenRows = new ArrayList<>();
        IntStream.range(start + 1, end)
                 .forEach(x -> betweenRows.add(rows[x]));
        return betweenRows;
    }

    public int diff(Row row) {
        return row.ordinal() - ordinal();
    }
}
