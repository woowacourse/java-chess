package chess.domain.board.coordinate;

import java.util.Arrays;

public enum Column {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String name;
    private final int value;

    Column(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static Column of(String value) {
        return Arrays.stream(values())
                .filter(column -> column.name.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 column은 존재하지 않습니다."));
    }

    public Column move(int distance) {
        return Arrays.stream(values())
                .filter(column -> column.value == this.value + distance)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("조건에 맞는 Column이 존재하지 않습니다."));
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int gap(Column column) {
        return column.value - value;
    }

    @Override
    public String toString() {
        return name;
    }
}
