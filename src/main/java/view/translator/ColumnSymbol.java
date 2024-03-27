package view.translator;

import java.util.Arrays;

public enum ColumnSymbol {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String identifier;
    private final int position;

    ColumnSymbol(String identifier, int position) {
        this.identifier = identifier;
        this.position = position;
    }

    public static ColumnSymbol from(String identifier) {
        return Arrays.stream(ColumnSymbol.values())
                .filter(columnSymbol -> identifier.toLowerCase().equals(columnSymbol.identifier))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하는 식별자가 아닙니다."));
    }

    public int getPosition() {
        return this.position;
    }

    public static int size() {
        return values().length;
    }
}
