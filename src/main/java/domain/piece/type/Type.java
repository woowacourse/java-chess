package domain.piece.type;

public enum Type {
    KING('k',0),
    QUEEN('q',9),
    ROOK('r',5),
    BISHOP('b',3),
    KNIGHT('n',2.5),
    PAWN('p',1),
    EMPTY('.',0);

    private final char label;
    private final double score;

    Type(char label, double score) {
        this.label = label;
        this.score = score;
    }

    public char getLabel() {
        return label;
    }

    public double getScore() {
        return score;
    }
}
