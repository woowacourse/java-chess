package chess.domain.movement.direction;

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
        return new Position(from.file() + 1, from.rank() + 1);
    }
}
