package chess.domain.piece;

public enum PieceProperty {
    BISHOP("b", 3),
    KING("k", 0),
    KNIGHT("n", 2.5),
    PAWN("p", 1),
    QUEEN("q", 9),
    ROOK("r", 5),
    NULL_PIECE(".", 0),
    ;

    private final String character;
    private final double score;

    PieceProperty(final String value, final double score) {
        this.character = value;
        this.score = score;
    }

    public String getCharacter() {
        return character;
    }

    public double getScore() {
        return score;
    }
}
