package chess.domain.piece;

public class Pawn extends Piece {
    public Pawn(final PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public Piece getNextPiece() {
        if (pieceType == PieceType.FIRST_WHITE_PAWN | pieceType == PieceType.WHITE_PAWN) {
            return Piece.of(PieceType.WHITE_PAWN);
        }
        return Piece.of(PieceType.BLACK_PAWN);
    }
}
