package chess.domain.movement.direction;

import chess.domain.Position;

public class RightDirection extends StraightDirection {

    public RightDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position from) {
        if (from.isMaximumColumn()) {
            return from;
        }
        return new Position(from.file() + 1, from.rank());
    }
}
