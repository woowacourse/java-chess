package chess.domain.piece;

public class Knight extends Piece {
    public Knight(final PieceType pieceType) {
        super(pieceType);
    }

    @Override
    public Piece getNextPiece() {
        if (pieceType == PieceType.WHITE_KNIGHT) {
            return Piece.of(PieceType.WHITE_KNIGHT);
        }
        return Piece.of(PieceType.BLACK_KNIGHT);
    }
}

