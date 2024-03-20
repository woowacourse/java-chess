package chess.domain.direction;

import chess.domain.Position;

public class RightDirection extends StraightDirection {

    RightDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position from) {
        if (from.isMaximumColumn()) {
            return from;
        }
        return new Position(from.row(), from.column() + 1);
    }
}
