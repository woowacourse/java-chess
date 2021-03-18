package domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public enum Column {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String value;
    private static Column[] columns = values();

    Column(String value) {
        this.value = value;
    }

    public Column moveBy(int value) {
        if ((this.ordinal() + value) < 0 || this.ordinal() + value >= columns.length) {
            throw new IllegalArgumentException("범위를 넘어가는 move 입니다");
        }
        return columns[(this.ordinal() + value)];
    }

    public String value() {
        return value;
    }

    public List<Column> getBetween(Column to) {
        int start = Math.min(this.ordinal(), to.ordinal());
        int end = Math.max(this.ordinal(), to.ordinal());
        List<Column> betweenColumns = new ArrayList<>();
        IntStream.range(start + 1, end)
                 .forEach(x -> betweenColumns.add(columns[x]));
        return betweenColumns;
    }

    public int diff(Column column) {
        return column.ordinal() - ordinal();
    }
}
