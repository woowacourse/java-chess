package chess.domain.piece;

public enum PieceType {

    BISHOP("b", 3),
    KING("k", 0),
    ROOK("r", 5),
    KNIGHT("n", 2.5),
    QUEEN("q", 9),
    PAWN("p", 1);

    private final String signature;
    private final double score;

    PieceType(String signature, double score) {
        this.signature = signature;
        this.score = score;
    }

    public String getSignature() {
        return signature;
    }

    public double getScore() {
        return score;
    }
}
