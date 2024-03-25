package chess.domain.piece;

public class EmptyPiece extends Piece {

    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece();

    private EmptyPiece() {
        super(Color.EMPTY);
    }

    public static EmptyPiece of() {
        return EMPTY_PIECE;
    }

    @Override
    public boolean canMoveMoreThenOnce() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
