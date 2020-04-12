package chess.domain.piece;

public class King extends Piece {
    public King(final PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public Piece getNextPiece() {
        if (pieceType == PieceType.WHITE_KING) {
            return Piece.of(PieceType.WHITE_KING);
        }
        return Piece.of(PieceType.BLACK_KING);
    }
}
