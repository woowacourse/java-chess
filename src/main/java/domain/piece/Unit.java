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

    public String getSymbol(Player player) {
        if (player == Player.BLACK)
            return symbol;
        return symbol.toLowerCase();
    }
}
