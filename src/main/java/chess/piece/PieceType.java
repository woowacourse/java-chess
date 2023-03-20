package chess.piece;

public enum PieceType {

    PAWN('P', 1.0),
    ROOK('R', 5.0),
    KNIGHT('N', 2.5),
    BISHOP('B', 3.0),
    QUEEN('Q', 9.0),
    KING('K', 0.0),
    EMPTY(' ', 0.0);

    private final char symbol;

    private final double value;

    PieceType(
            final char symbol,
            final double value
    ) {
        this.symbol = symbol;
        this.value = value;
    }

    public char symbol() {
        return symbol;
    }

    public double value() {
        return value;
    }

    @Override
    public String toString() {
        return "PieceType{" +
                "symbol=" + symbol +
                ", value=" + value +
                "} " + super.toString();
    }
}
