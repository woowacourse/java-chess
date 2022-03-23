package domain.piece;

public enum PieceSymbol {

    King("K"),
    Queen("Q"),
    Bishop("B"),
    Knight("N"),
    Rook("R"),
    Pawn("P");

    private String symbol;

    PieceSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public String symbol(final Player player) {
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
