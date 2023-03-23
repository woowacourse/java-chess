package chess.domain.chesspiece;

public enum PieceInfo {
    EMPTY_PIECE("EMPTY_PIECE"),
    PAWN("PAWN"),
    ROOK("ROOK"),
    BISHOP("BISHOP"),
    KNIGHT("KNIGHT"),
    QUEEN("QUEEN"),
    KING("KING");

    private final String name;

    PieceInfo(final String name) {
        this.name = name;
    }
}
