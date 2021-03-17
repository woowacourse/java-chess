package chess.domain;

import java.util.Arrays;

public enum Column {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String name;

    Column(String name){
        this.name = name;
    }

    public static Column getColumn(String value) {
        return Arrays.stream(values())
            .filter(column -> column.name.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 열입니다"));
    }
}
