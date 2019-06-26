package chess.domain.pieces;

public enum Type {
    BLANK(0, "B"),
    KING(0, "K"),
    QUEEN(9, "Q"),
    BISHOP(3, "B"),
    KNIGHT(2.5, "N"),
    ROOK(5, "R"),
    PAWN(1, "P");

    private double score;
    private String initial;

    Type(double score, String initial) {
        this.score = score;
        this.initial = initial;
    }

    public double getScore() {
        return score;
    }

    public String getInitial() {
        return initial;
    }
}
