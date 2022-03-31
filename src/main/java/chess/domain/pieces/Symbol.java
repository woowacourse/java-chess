package chess.domain.pieces;

public enum Symbol {

    BISHOP("B"),
    KING("K"),
    KNIGHT("N"),
    PAWN("P"),
    QUEEN("Q"),
    ROOK("R");

    private final String value;

    Symbol(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
