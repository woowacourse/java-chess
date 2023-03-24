package chess.domain.piece;

public enum PieceType {
    QUEEN("q", "Q", 9),
    ROOK("q", "Q", 5),
    BISHOP("q", "Q", 3),
    KNIGHT("q", "Q", 2.5),
    PAWN("q", "Q", 1),
    VERTICAL_PAWN("q", "Q", 0.5),
    KING("q", "Q", 0),
    EMPTY("q", "Q", -1);

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
