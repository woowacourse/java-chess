package chess.domain.movement.direction;

import chess.domain.Position;

public class UpLeftDirection extends StraightDirection {

    public UpLeftDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position from) {
        if (from.isMaximumRow() || from.isMinimumColumn()) {
            return from;
        }
        return new Position(from.file() - 1, from.rank() + 1);
    }
}
