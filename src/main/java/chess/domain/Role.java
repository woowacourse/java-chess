package chess.domain;

public enum Role {
    KING("K"),
    QUEEN("Q"),
    BISHOP("B"),
    KNIGHT("N"),
    ROCK("R"),
    PAWN("P"),
    EMPTY("E");

    private final String initial;

    Role(final String initial) {
        this.initial = initial;
    }
}
