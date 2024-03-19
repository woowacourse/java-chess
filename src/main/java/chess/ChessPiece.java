package chess;

public enum ChessPiece {
    BLACK_KING("K"),
    WHITE_KING("k"),
    BLACK_QUEEN("Q"),
    WHITE_QUEEN("q"),
    BLACK_ROOK("R"),
    WHITE_ROOK("r"),
    BLACK_BISHOP("B"),
    WHITE_BISHOP("b"),
    BLACK_KNIGHT("N"),
    WHITE_KNIGHT("n"),
    BLACK_PAWN("P"),
    WHITE_PAWN("p"),
    NONE(".");

    private final String name;

    ChessPiece(String name) {
        this.name = name;
    }
}
