package chess.domain;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Side.NO_SIDE);
    }

    @Override
    boolean isMovable(final Square from, final Square to, final Piece piece) {
        throw new UnsupportedOperationException();
    }
}
