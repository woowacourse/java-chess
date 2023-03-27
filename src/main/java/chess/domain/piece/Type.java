package chess.domain.piece;

public enum Type {
    KING("King"),
    QUEEN("Queen"),
    BISHOP("Bishop"),
    ROOK("Rook"),
    KNIGHT("Knight"),
    PAWN("Pawn");

    private final String label;

    Type(final String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
