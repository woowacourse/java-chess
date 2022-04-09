package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Column {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h"),
    ;

    private static final String INVALID_COLUMN_EXCEPTION = "존재하지 않는 열입니다.";
    private static final int MAX_COLUMN_VALUE = 8;

    private final int value;
    private final String name;

    Column(final int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Column from(String rawColumn) {
        return Arrays.stream(values())
                .filter(column -> column.name.equals(rawColumn))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COLUMN_EXCEPTION));
    }

    private static Column from(int value) {
        return Arrays.stream(values())
                .filter(column -> column.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COLUMN_EXCEPTION));
    }

    public Column flip() {
        return Arrays.stream(Column.values())
                .filter(it -> it.value == (MAX_COLUMN_VALUE - this.value + 1))
                .findFirst()
                .orElseThrow();
    }

    public int distance(Column otherColumn) {
        return Math.abs(this.value - otherColumn.value);
    }

    public List<Column> pathTo(Column otherColumn) {
        if (this.value < otherColumn.value) {
            return upPathTo(otherColumn);
        }
        return downPathTo(otherColumn);
    }

    private List<Column> upPathTo(Column otherColumn) {
        List<Column> path = new ArrayList<>();
        for (int i = this.value + 1; i < otherColumn.value; i++) {
            path.add(Column.from(i));
        }
        return path;
    }

    private List<Column> downPathTo(Column otherColumn) {
        List<Column> path = new ArrayList<>();
        for (int i = this.value - 1; i > otherColumn.value; i--) {
            path.add(Column.from(i));
        }
        return path;
    }

    public String getName() {
        return name;
    }
}
