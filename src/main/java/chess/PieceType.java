package chess;

public enum PieceType {
    BISHOP("b"),
    KING("k"),
    KNIGHT("h"),
    PAWN("p"),
    QUEEN("q"),
    ROOK("r");

    private final String display;

    PieceType(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
