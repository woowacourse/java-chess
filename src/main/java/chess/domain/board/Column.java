package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Column {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    ;

    private static final int MAX_VALUE = 9;
    private static final String ERROR_NO_SUCH_COLUMN = "존재하지 않는 열입니다.";

    private final int value;

    Column(final int value) {
        this.value = value;
    }

    private static Column from(int value) {
        return Arrays.stream(values())
                .filter(column -> column.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_SUCH_COLUMN));
    }

    Column flip() {
        return from(MAX_VALUE - this.value);
    }

    int distance(Column otherColumn) {
        return Math.abs(this.value - otherColumn.value);
    }

    List<Column> pathTo(Column otherColumn) {
        if (this.value < otherColumn.value) {
            return this.upPathTo(otherColumn);
        }
        return this.downPathTo(otherColumn);
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

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
