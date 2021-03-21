package chess.domain.piece;

public enum Shape {
    ROOK("R", 5),
    KNIGHT("N", 2.5),
    BISHOP("B", 3),
    QUEEN("Q", 9),
    KING("K", 0),
    PAWN("P", 1);

    private final String notation;
    private final double score;

    Shape(final String notation, final double score) {
        this.notation = notation;
        this.score = score;
    }

    public String getNotation(final Color color) {
        if (color == Color.BLACK) {
            return notation.toUpperCase();
        }

        return notation.toLowerCase();
    }

    public double getScore() {
        return score;
    }

}
