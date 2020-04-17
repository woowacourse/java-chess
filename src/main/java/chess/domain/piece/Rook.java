package chess.domain.piece;

public class Rook extends Piece {
    public Rook(final PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public Piece getNextPiece() {
        if (pieceType == PieceType.WHITE_ROOK) {
            return Piece.of(PieceType.WHITE_ROOK);
        }
        return Piece.of(PieceType.BLACK_ROOK);
    }
}
