package view.util;

import java.util.Arrays;

public enum ColumnSymbol {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String identifier;
    private final int position;

    ColumnSymbol(String identifier, int position) {
        this.identifier = identifier;
        this.position = position;
    }

    public static ColumnSymbol from(String identifier) {
        return Arrays.stream(ColumnSymbol.values())
                .filter(columnSymbol -> identifier.equals(columnSymbol.identifier))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 식별자가 아닙니다."));
    }

    public int getPosition() {
        return this.position;
    }
}
