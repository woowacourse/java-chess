package chess.domain.piece;

import chess.domain.position.Movement;

public final class None extends Piece {
    private static final String NONE = " ";

    public None(Color color) {
        super(color, 0);
    }

    @Override
    public String getEmoji() {
        return NONE;
    }

    @Override
    public boolean canMove(Movement movement, Piece target) {
        return false;
    }

    @Override
    public boolean isNone() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
