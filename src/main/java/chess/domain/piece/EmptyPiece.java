package chess.domain.piece;

import chess.domain.position.Position;

public final class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Color.NONE, Type.EMPTY);
    }

    @Override
    public boolean isRightMovement(final Position from, final Position to, final boolean isEmptyTarget) {
        return false;
    }

    @Override
    public boolean isJumpable() {
        return false;
    }
}
