package chess.domain.position;

import java.util.Arrays;

public enum Column {
    A('a', 1),
    B('b', 2),
    C('c', 3),
    D('d', 4),
    E('e', 5),
    F('f', 6),
    G('g', 7),
    H('h', 8);

    private final char value;
    private final int number;

    Column(char value, int number) {
        this.value = value;
        this.number = number;
    }

    public static Column find(int number) {
        return Arrays.stream(values())
                .filter(i -> i.number == number)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Column 값 입니다."));
    }

    public static Column find(char value) {
        return Arrays.stream(values())
                .filter(i -> i.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Column 값 입니다."));
    }

    public int getDifference(Column col) {
        return this.value - col.value;
    }

    public String getSymbol() {
        return String.valueOf(value);
    }

    public Column plusColumn(int number) {
        return find(this.number + number);
    }
}
