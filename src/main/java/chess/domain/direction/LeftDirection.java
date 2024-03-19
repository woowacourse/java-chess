package chess.domain.direction;

import chess.domain.Position;

public class LeftDirection extends Direction {

    LeftDirection(final int moveCount) {
        super(moveCount);
    }
    

    @Override
    Position next(final Position from) {
        if (from.isMinimumColumn()) {
            return from;
        }
        return new Position(from.row(), from.column() - 1);
    }
}
