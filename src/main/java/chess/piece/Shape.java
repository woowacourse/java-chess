package chess.piece;

public enum Shape {

    PAWN("p", "P", 1),
    KNIGHT("n", "N", 2.5),
    BISHOP("b", "B", 3),
    ROOK("r", "R", 5),
    QUEEN("q", "Q", 9),
    KING("k", "K", 0),
    EMPTY(".", ".", 0);

    private final String whiteName;
    private final String blackName;
    private final double score;

    Shape(String whiteName, String blackName, double score) {
        this.whiteName = whiteName;
        this.blackName = blackName;
        this.score = score;
    }

    public String getWhiteName() {
        return whiteName;
    }

    public String getBlackName() {
        return blackName;
    }

    public double getScore() {
        return score;
    }
}
