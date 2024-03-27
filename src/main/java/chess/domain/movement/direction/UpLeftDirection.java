package chess.domain.movement.direction;

import chess.domain.position.Position;

public class UpLeftDirection extends StraightDirection {

    public UpLeftDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMaximumRank() || position.isMinimumFile()) {
            return position;
        }
        return new Position(position.file() - 1, position.rank() + 1);
    }
}
