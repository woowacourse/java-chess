package chess.domain.movement.direction;

import chess.domain.position.Position;

public class RightDirection extends StraightDirection {

    public RightDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMaximumFile()) {
            return position;
        }
        return new Position(position.file() + 1, position.rank());
    }
}
