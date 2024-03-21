package chess.domain.movement.direction;

import chess.domain.Position;

public class DownLeftDirection extends StraightDirection {

    public DownLeftDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position from) {
        if (from.isMinimumRow() || from.isMinimumColumn()) {
            return from;
        }
        return new Position(from.file() - 1, from.rank() - 1);
    }
}
