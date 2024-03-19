package chess.domain.direction;

import chess.domain.Position;

public class UpLeftDirection extends Direction {

    UpLeftDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position from) {
        if (from.isMaximumRow() || from.isMinimumColumn()) {
            return from;
        }
        return new Position(from.row() + 1, from.column() - 1);
    }
}
