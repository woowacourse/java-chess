package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Column {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    Column(int value) {
        this.value = value;
    }

    public static Column of(final String value) {
        return Arrays.stream(values())
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("'" + value + "'는 올바르지 않은 컬럼입니다."));
    }

    public boolean isSame(Column column) {
        return this == column;
    }

    public int gap(Column column) {
        return Math.abs(this.value - column.value);
    }

    public List<Column> columnPaths(Column targetColumn) {
        int gap = gap(targetColumn);
        if (targetColumn.value > this.value) {
            return upperColumnsRange(this.value, gap);
        }
        List<Column> columns = upperColumnsRange(targetColumn.value, gap);
        Collections.reverse(columns);
        return columns;
    }

    private List<Column> upperColumnsRange(int startValue, int gap) {
        List<Column> columns = new ArrayList<>();
        for (int i = 1; i < gap; i++) {
            columns.add(find(startValue + i));
        }
        return columns;
    }

    private Column find(int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("'" + value + "'는 올바르지 않은 컬럼입니다."));
    }

}
