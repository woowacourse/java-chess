package domain.piece;

import domain.Player;

public enum PieceSymbol {
    KING("K"),
    QUEEN("Q"),
    BISHOP("B"),
    KNIGHT("N"),
    ROOK("R"),
    PAWN("P"),
    NULL(".");

    private final String symbol;

    PieceSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }

    public String symbol(final Player player) {
        if (player.equals(Player.NULL)) {
            return NULL.symbol;
        }
        if (player == Player.BLACK) {
            return symbol;
        }
        return symbol.toLowerCase();
    }
}
