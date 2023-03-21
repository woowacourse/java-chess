package chess.model.piece;

import chess.model.board.DefaultColor;
import chess.model.board.DefaultType;
import chess.model.position.Direction;

public class Empty extends Piece {

    private static final Empty EMPTY = new Empty();

    private Empty() {
        super(DefaultColor.EMPTY, DefaultType.EMPTY);
    }

    public static Empty getInstance() {
        return EMPTY;
    }

    @Override
    protected boolean isRightDirection(final Direction direction) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
