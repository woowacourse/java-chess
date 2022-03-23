package chess.domain.board;

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

    Column(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public int subtract(final Column column) {
        return column.value - this.value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
