package chess.domain.direction;

import chess.domain.Position;

public class DownRightDirection extends Direction {

    DownRightDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position from) {
        if (from.isMinimumRow() || from.isMaximumColumn()) {
            return from;
        }

        return new Position(from.row() - 1, from.column() + 1);
    }
}
