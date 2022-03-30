package chess.domain.piece;

public enum PieceName {

    KING("K"),
    QUEEN("Q"),
    ROOK("R"),
    BISHOP("B"),
    KNIGHT("N"),
    PAWN("P"),
    EMPTY(".");

    private final String value;

    PieceName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
