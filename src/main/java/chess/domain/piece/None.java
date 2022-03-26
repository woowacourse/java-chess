package chess.domain.piece;

import chess.domain.position.Direction;

public final class None extends Piece {
    private static final String NONE = "·";

    None(Color color) {
        super(color);
    }

    @Override
    public String getEmoji() {
        return NONE;
    }

    @Override
    public boolean canMove(Direction direction, Piece target) {
        return false;
    }
}
