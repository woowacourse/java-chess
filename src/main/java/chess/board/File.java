package chess.board;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String symbol;
    private final int value;

    File(final String symbol, final int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public int subtract(File file) {
        return value - file.value;
    }
}
