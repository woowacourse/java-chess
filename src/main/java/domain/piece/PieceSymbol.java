package domain.piece;

import domain.Player;

public enum PieceSymbol {
    King("K"),
    Queen("Q"),
    Bishop("B"),
    Knight("N"),
    Rook("R"),
    Pawn("P");

    private final String symbol;

    PieceSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }

    public String symbol(final Player player) {
        if (player == Player.BLACK) {
            return symbol;
        }
        return symbol.toLowerCase();
    }
}
