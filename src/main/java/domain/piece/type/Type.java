package domain.piece.type;

public enum Type {
    KING('k'),
    QUEEN('q'),
    ROOK('r'),
    BISHOP('b'),
    KNIGHT('n'),
    PAWN('p'),
    EMPTY('.');

    private final char label;

    Type(char label) {
        this.label = label;
    }

    public char getLabel() {
        return label;
    }
}
