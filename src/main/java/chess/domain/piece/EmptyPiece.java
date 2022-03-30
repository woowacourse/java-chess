package chess.domain.piece;

import chess.domain.position.Position;

public final class EmptyPiece extends Piece {

    private static final String NAME = ".";

    public EmptyPiece() {
        super(Color.NONE, NAME);
    }

    @Override
    public boolean isRightMovement(final Position from,
                                   final Position to,
                                   final boolean isEmptyTarget) {
        return false;
    }

    @Override
    public boolean isSameColor(final Color color) {
        return false;
    }

    @Override
    public boolean isJumpable() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
