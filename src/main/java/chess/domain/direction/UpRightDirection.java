package chess.domain.direction;

import chess.domain.Position;

public class UpRightDirection extends StraightDirection {

    public UpRightDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position from) {
        if (from.isMaximumColumn() || from.isMaximumRow()) {
            return from;
        }
        return new Position(from.row() + 1, from.column() + 1);
    }
}
