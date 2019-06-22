package chess.domain.pieces;

public enum Type {
    BLANK(0),
    KING(0),
    QUEEN(9),
    BISHOP(3),
    KNIGHT(2.5),
    ROOK(5),
    PAWN(1);

    private double score;

    Type(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
