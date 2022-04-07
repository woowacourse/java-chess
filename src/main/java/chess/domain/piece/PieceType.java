package chess.domain.piece;

public enum PieceType {

    KING("K", 0),
    QUEEN("Q", 9),
    ROOK("R", 5),
    BISHOP("B", 3),
    KNIGHT("N", 2.5),
    PAWN("P", 1),
    EMPTY(".", 0);

    private final String symbol;
    private final double point;

    PieceType(String symbol, double point) {
        this.symbol = symbol;
        this.point = point;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPoint() {
        return point;
    }
}
