package chess.domain.piece;

public enum PieceType {

    PAWN("P", "p", 1.0),
    ROOK("R", "r", 5.0),
    KNIGHT("N", "n", 2.5),
    BISHOP("B", "b", 3.0),
    QUEEN("Q", "q", 9.0),
    KING("K", "k", 0);

    private final String blackName;
    private final String whiteName;
    private final double score;

    PieceType(String blackName, String whiteName, double score) {
        this.blackName = blackName;
        this.whiteName = whiteName;
        this.score = score;
    }

    public String getBlackName() {
        return blackName;
    }

    public String getWhiteName() {
        return whiteName;
    }

    public double getScore() {
        return score;
    }
}
