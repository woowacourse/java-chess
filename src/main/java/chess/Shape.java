package chess;

public enum Shape {

    PAWN("P", "p"),
    KNIGHT("N", "n"),
    BISHOP("B", "b"),
    ROOK("R", "r"),
    QUEEN("Q", "q"),
    KING("K", "k");

    private final String black;
    private final String white;

    Shape(String black, String white) {
        this.black = black;
        this.white = white;
    }
}
