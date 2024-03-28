package chess.domain.movement.direction;

import chess.domain.position.Position;

public class UpDirection extends StraightDirection {

    public UpDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMaximumRank()) {
            return position;
        }
        return position.up();
    }
}
