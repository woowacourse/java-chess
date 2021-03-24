package chess.domain.piece;

public enum Symbol {
    KING("k", "K"),
    QUEEN("q", "Q"),
    BISHOP("b", "B"),
    KNIGHT("n", "N"),
    ROOK("r", "R"),
    PAWN("p", "P"),
    EMPTY(".", ".");

    private final String white;
    private final String black;

    Symbol(String white, String black) {
        this.white = white;
        this.black = black;
    }

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }
}
