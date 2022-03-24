package chess.domain.piece;

public abstract class ValidPiece extends Piece {

    protected ValidPiece(final Color color, final double point) {
        super(color, point);
    }

    @Override
    public boolean isBlank() {
        return false;
    }
}
