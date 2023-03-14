package chess.domain;

public enum File {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private final int position;
    private final String symbol;

    File(final int position, final String symbol) {
        this.position = position;
        this.symbol = symbol;
    }

    public int distanceTo(final File other) {
        return Math.abs(position - other.position);
    }

    public int getPosition() {
        return position;
    }
}
