package chess.domain.piece;

public enum PieceType {
    KING("k"),
    QUEEN("q"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    PAWN("p"),
    INITIAL_PAWN("p");

    private final String name;

    PieceType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
