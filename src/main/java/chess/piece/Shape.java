package chess.piece;

public enum Shape {

    PAWN("p", "P"),
    KNIGHT("n", "N"),
    BISHOP("b", "B"),
    ROOK("r", "R"),
    QUEEN("q", "Q"),
    KING("k", "K"),
    BLANK(".", ".");

    private final String whiteName;
    private final String blackName;

    Shape(String whiteName, String blackName) {
        this.whiteName = whiteName;
        this.blackName = blackName;
    }

    public String getWhiteName() {
        return whiteName;
    }

    public String getBlackName() {
        return blackName;
    }
}
