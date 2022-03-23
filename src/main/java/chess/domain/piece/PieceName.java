package chess.domain.piece;

public enum PieceName {

    KING("K"),
    QUEEN("Q"),
    ROOK("R"),
    BISHOP("B"),
    KNIGHT("N"),
    PAWN("P"),
    EMPTY(".");

    private final String symbol;

    PieceName(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
