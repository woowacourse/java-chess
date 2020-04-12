package chess.domain.piece;

public class Bishop extends Piece {
    public Bishop(final PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public Piece getNextPiece() {
        if (pieceType == PieceType.WHITE_BISHOP) {
            return Piece.of(PieceType.WHITE_BISHOP);
        }
        return Piece.of(PieceType.BLACK_BISHOP);
    }
}
