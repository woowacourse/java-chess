package chess.domain.piece;

public class Knight extends AbstractPiece {
    Knight(PieceColor pieceColor) {
        super(pieceColor, PieceType.KNIGHT);
    }

    @Override
    public boolean isAbleToJump() {
        return true;
    }
}
