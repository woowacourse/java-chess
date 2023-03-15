package chess.domain;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Team.NO_TEAM);
    }

    @Override
    boolean isMovable(final Square from, final Square to, final Piece piece) {
        throw new UnsupportedOperationException();
    }
}
