package chess.domain.piece;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(new Name("."), Color.NONE);
    }

    @Override
    protected boolean isEmpty() {
        return true;
    }
}
