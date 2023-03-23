package chess.model.piece;

import chess.model.Color;
import chess.model.position.Distance;

public class Empty extends Piece {

    private static final Empty EMPTY = new Empty();

    private Empty() {
        super(DefaultColor.EMPTY, DefaultType.EMPTY);
    }

    public static Empty getInstance() {
        return EMPTY;
    }

    @Override
    public boolean isMovable(final Distance distance, final Color targetColor) {
        return false;
    }

    @Override
    public Piece update() {
        return this;
    }

    @Override
    public boolean isEmpty() {
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
