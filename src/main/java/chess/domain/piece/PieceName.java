package chess.domain.piece;

public enum PieceName {
    BISHOP("b"),
    KING("k"),
    KNIGHT("n"),
    PAWN("p"),
    QUEEN("q"),
    ROOK("r"),
    NULL_PIECE("."),
    ;

    private final String character;

    PieceName(final String value) {
        this.character = value;
    }

    public String getCharacter() {
        return character;
    }
}
