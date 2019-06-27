package model.piece;

public enum Score {
    KING(.0),
    QUEEN(9.0),
    ROOK(5.0),
    BISHOP(3.0),
    KNIGHT(2.5),
    PAWN(1.0),
    PAWN_HALF(0.5);

    private double score;

    Score(double score) {
        this.score = score;
    }

    public double val() {
        return this.score;
    }
}