package domain.piece;

public enum PieceName {

    PAWN("P", "p"),
    ROOK("R", "r"),
    KNIGHT("N", "n"),
    BISHOP("B", "b"),
    QUEEN("Q", "q"),
    KING("K", "k");

    private final String black;
    private final String white;

    PieceName(String black, String white) {
        this.black = black;
        this.white = white;
    }

    public String getBlack() {
        return black;
    }

    public String getWhite() {
        return white;
    }
}
