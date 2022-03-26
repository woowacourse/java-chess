package chess.domain.piece;

import chess.domain.position.Position;

public final class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Color.NONE, ".");
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final boolean isEmptyTarget) {
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
    public boolean isKing() {
        return false;
    }
}
