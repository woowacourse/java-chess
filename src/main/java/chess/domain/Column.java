package chess.domain;

import java.util.Arrays;

public enum Column {
    A(1, "a"),
    B(2, "b"),
    C(3,"c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private final int sequence;
    private final String name;

    Column(int sequence, String name) {
        this.sequence = sequence;
        this.name = name;
    }

    public static Column findColumn(String name) {
        return Arrays.stream(Column.values())
                .filter(column -> column.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 컬럼을 찾을 수 없습니다."));
    }

    public String getName() {
        return name;
    }
}
