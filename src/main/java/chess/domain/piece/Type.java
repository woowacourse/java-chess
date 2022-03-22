package chess.domain.piece;

public enum Type {

    KING("K"),
    QUEEN("Q"),
    BISHOP("B"),
    KNIGHT("N"),
    ROOK("R"),
    PAWN("P");

    private final String type;

    Type(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
