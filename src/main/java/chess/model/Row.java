package chess.model;

public enum Row {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private String symbol;

    Row(final String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
