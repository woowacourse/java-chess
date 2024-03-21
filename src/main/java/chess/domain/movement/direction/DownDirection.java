package chess.domain.movement.direction;

import chess.domain.Position;

public class DownDirection extends StraightDirection {

    public DownDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position from) {
        if (from.isMinimumRow()) {
            return from;
        }
        return new Position(from.file(), from.rank() - 1);
    }
}
