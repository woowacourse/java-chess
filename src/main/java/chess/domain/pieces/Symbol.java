package chess.domain.pieces;

public enum Symbol {

    BISHOP("-bishop.png", 3),
    KING("-king.png", 0),
    KNIGHT("-knight.png", 2.5),
    PAWN("-pawn.png", 1),
    QUEEN("-queen.png", 9),
    ROOK("-rook.png", 5),
    BLANK("blank.png", 0),
    ;

    private final String value;
    private final double score;

    Symbol(String value, double score) {
        this.value = value;
        this.score = score;
    }

    public String value() {
        return value;
    }

    public double score() {
        return score;
    }
}
