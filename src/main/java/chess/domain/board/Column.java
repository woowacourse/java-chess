package chess.domain.board;

import java.util.Arrays;
import java.util.Locale;

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

    public static Column from(int value) {
        return Arrays.stream(values())
                .filter(column -> column.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 위치입니다."));
    }

    public static Column from(String name) {
        try {
            return Column.valueOf(name.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("올바르지 않은 위치입니다.");
        }
    }

    public int calculateDifference(Column target) {
        return this.value - target.value;
    }

    public Column move(int columnDifference) {
        return from(value + columnDifference);
    }
}
