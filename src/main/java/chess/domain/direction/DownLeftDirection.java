package chess.domain.direction;

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
        return new Position(from.row() - 1, from.column() - 1);
    }
}
