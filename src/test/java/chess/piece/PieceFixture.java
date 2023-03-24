package chess.piece;

public enum PieceFixture {
    ROOK(Rook.class),
    KNIGHT(Knight.class),
    BISHOP(Bishop.class),
    KING(King.class),
    QUEEN(Queen.class),
    PAWN(Pawn.class),
    ;

    private final Class<? extends Piece> pieceClass;

    PieceFixture(final Class<? extends Piece> pieceClass) {
        this.pieceClass = pieceClass;
    }

    public Class<? extends Piece> getPieceClass() {
        return pieceClass;
    }
}
