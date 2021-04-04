package chess.domain.piece;

public enum Shape {
    R("R", 5),
    N("N", 2.5),
    B("B", 3),
    Q("Q", 9),
    K("K", 0),
    P("P", 1);

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

    @Override
    public String toString() {
        return notation;
    }
}
