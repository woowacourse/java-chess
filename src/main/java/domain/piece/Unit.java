package domain.piece;

public enum Unit {

    King("K"),
    Queen("Q"),
    Bishop("B"),
    Knight("N"),
    Rook("R"),
    Pawn("P");

    private String symbol;

    Unit(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol(final Player player) {
        if (player == Player.BLACK) {
            return symbol;
        }
        return symbol.toLowerCase();
    }

    @Override
    public String toString() {
        return "Unit{" +
                "symbol='" + symbol + '\'' +
                '}';
    }
}
