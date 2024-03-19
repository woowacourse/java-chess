package chess.domain.direction;

import chess.domain.Position;

public class UpDirection extends Direction {

    UpDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position from) {
        if (from.isMaximumRow()) {
            return from;
        }
        return new Position(from.row() + 1, from.column());
    }
}
