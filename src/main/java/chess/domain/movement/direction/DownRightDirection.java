package chess.domain.movement.direction;

import chess.domain.Position;

public class DownRightDirection extends StraightDirection {

    public DownRightDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMinimumRank() || position.isMaximumFile()) {
            return position;
        }
        return new Position(position.file() + 1, position.rank() - 1);
    }
}
