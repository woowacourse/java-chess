package chess.domain.piece;

import chess.domain.board.Position;

public class Empty extends Piece {

    private Empty() {
        super(Color.EMPTY, PieceType.EMPTY);
    }

    public static Empty create() {
        return new Empty();
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
