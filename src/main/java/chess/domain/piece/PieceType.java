package chess.domain.piece;

public enum PieceType {
    QUEEN("q", "Q", 9),
    ROOK("r", "R", 5),
    BISHOP("b", "B", 3),
    KNIGHT("n", "N", 2.5),
    PAWN("p", "P", 1),
    VERTICAL_PAWN("p", "P", 0.5),
    KING("k", "K", 0),
    EMPTY(".", ".", -1);

    private final String whiteInitial;
    private final String blackInitial;
    private final double value;

    PieceType(final String whiteInitial, final String blackInitial, final double value) {
        this.whiteInitial = whiteInitial;
        this.blackInitial = blackInitial;
        this.value = value;
    }

    public double value() {
        return value;
    }

    public String whiteInitial() {
        return whiteInitial;
    }

    public String blackInitial() {
        return blackInitial;
    }
}
