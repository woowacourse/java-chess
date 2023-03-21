package chess.domain.piece;

import chess.domain.position.Position;

public class Empty extends Piece {

    private static final Empty EMPTY = new Empty();

    private Empty() {
        super(Color.EMPTY, PieceType.EMPTY);
    }

    public static Empty getInstance() {
        return EMPTY;
    }

    @Override
    protected boolean isValidMove(final Position start, final Position end) {
        return false;
    }

    @Override
    protected boolean isValidTarget(final Piece target) {
        return false;
    }
}
