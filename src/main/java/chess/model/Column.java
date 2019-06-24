package chess.model;

public enum Column {
    _1("1"),
    _2("2"),
    _3("3"),
    _4("4"),
    _5("5"),
    _6("6"),
    _7("7"),
    _8("8");

    private String symbol;

    Column(final String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
