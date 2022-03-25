package domain.piece.property;

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

    public String symbol(final Team Team) {
        if (Team == Team.BLACK) {
            return symbol;
        }
        return symbol.toLowerCase();
    }

    public String symbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "symbol='" + symbol + '\'' +
                '}';
    }
}
