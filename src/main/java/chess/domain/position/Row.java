package chess.domain.position;

import java.util.Arrays;

public enum Row {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    ;

    private final int index;

    Row(int index) {
        this.index = index;
    }

    public static Row from(String value) {
        validate(value);
        return find(value.charAt(0) - 'a');
    }

    private static void validate(String value) {
        validateAlphabet(value);
        validateSize(value);
    }

    private static void validateAlphabet(String value) {
        char row = value.charAt(0);
        if (row < 'a' || row > 'h') {
            throw new IllegalArgumentException("a~h까지 가능합니다.");
        }
    }

    private static void validateSize(String value) {
        if (value.length() != 1) {
            throw new IllegalArgumentException("a~h까지 가능합니다.");
        }
    }

    private static Row find(int index) {
        return Arrays.stream(values())
                .filter(row -> row.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("a~h까지 가능합니다."));
    }

    public Row update(int direction) {
        return Arrays.stream(values())
                .filter(row -> row.index == this.index + direction)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("a~h까지 가능합니다."));
    }

    public int getIndex() {
        return index;
    }

    public int compare(Row row) {
        return Integer.compare(row.index, index);
    }
}
