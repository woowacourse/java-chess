package chess.domain.piece;

public abstract class ValidPiece extends Piece {

    protected ValidPiece(Color color) {
        super(color);
    }

    @Override
    public boolean isBlank() {
        return false;
    }
}
