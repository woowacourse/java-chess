package domain.piece;

public enum PieceCategory {
    WHITE_PAWN("p"),
    WHITE_ROOK("r"),
    WHITE_KNIGHT("n"),
    WHITE_BISHOP("b"),
    WHITE_QUEEN("q"),
    WHITE_KING("k"),
    BLACK_PAWN("P"),
    BLACK_ROOK("R"),
    BLACK_KNIGHT("N"),
    BLACK_BISHOP("B"),
    BLACK_QUEEN("Q"),
    BLACK_KING("K"),
    EMPTY_PIECE(".");


    private final String pieceValue;

    PieceCategory(String pieceValue) {
        this.pieceValue = pieceValue;
    }

    public String getPieceValue() {
        return pieceValue;
    }
}
