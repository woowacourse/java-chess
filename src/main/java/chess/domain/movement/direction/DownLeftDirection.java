package chess.domain.movement.direction;

import chess.domain.position.Position;

public class DownLeftDirection extends StraightDirection {

    public DownLeftDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMinimumRank() || position.isMinimumFile()) {
            return position;
        }
        return new Position(position.file() - 1, position.rank() - 1);
    }
}
