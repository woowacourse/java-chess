package chess.domain.movement.direction;

import chess.domain.Position;

public class UpDirection extends StraightDirection {

    public UpDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position from) {
        if (from.isMaximumRow()) {
            return from;
        }
        return new Position(from.file(), from.rank() + 1);
    }
}
