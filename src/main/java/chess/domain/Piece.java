package chess.domain;

public enum Piece {
    KING("k"),
    QUEEN("q"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    PAWN("p");

    private final String name;

    Piece(final String name) {
        this.name = name;
    }
}
