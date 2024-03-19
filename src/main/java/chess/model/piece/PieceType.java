package chess.model.piece;

public enum PieceType {
    BLACK_PAWN("P", true),
    BLACK_ROOK("R", true),
    BLACK_KNIGHT("N", true),
    BLACK_BISHOP("B", true),
    BLACK_QUEEN("Q", true),
    BLACK_KING("K", true),
    WHITE_PAWN("p", false),
    WHITE_ROOK("r", false),
    WHITE_KNIGHT("n", false),
    WHITE_BISHOP("b", false),
    WHITE_QUEEN("q", false),
    WHITE_KING("k", false);

    private final String displayName;
    private final boolean side;

    PieceType(String displayName, boolean side) {
        this.displayName = displayName;
        this.side = side;
    }

    public String getDisplayName() {
        return displayName;
    }
}
