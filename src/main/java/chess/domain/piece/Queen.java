package chess.domain.piece;

public class Queen extends Piece {
    public Queen(final PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public Piece getNextPiece() {
        if (pieceType == PieceType.WHITE_QUEEN) {
            return Piece.of(PieceType.WHITE_QUEEN);
        }
        return Piece.of(PieceType.BLACK_QUEEN);
    }
}
