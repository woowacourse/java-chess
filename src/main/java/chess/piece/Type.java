package chess.piece;

public enum Type {

    KING(0, "k"),
    QUEEN(9, "q"),
    ROOK(5, "r"),
    BISHOP(3, "b"),
    KNIGHT(2.5, "n"),
    PAWN(1, "p"),
    BLANK(0, "."),
    ;

    private final double score;
    private final String symbol;

    Type(double score, String symbol) {
        this.score = score;
        this.symbol = symbol;
    }

    public String getSymbol(Color color) {
        if (color == Color.BLACK) {
            return symbol.toUpperCase();
        }
        return symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getScore() {
        return score;
    }
}
