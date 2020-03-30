package chess.domain.piece;

public enum Name {
    KING("K"),
    QUEEN("Q"),
    ROOK("R"),
    BISHOP("B"),
    KNIGHT("N"),
    PAWN("P"),
    EMPTY(".");

	private final String name;

    Name(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
