package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Square;

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
    public boolean canMove(Direction direction) {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
