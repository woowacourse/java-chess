package chess.domain.piece;

public enum Role {
    KING("K"),
    QUEEN("Q"),
    BISHOP("B"),
    KNIGHT("N"),
    ROOK("R"),
    PAWN("P"),
    EMPTY(".");

    private final String initial;

    Role(final String initial) {
        this.initial = initial;
    }

    public String getInitial() {
        return initial;
    }
}
