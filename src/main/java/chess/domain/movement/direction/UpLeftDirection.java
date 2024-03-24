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
        return position.up().left();
    }
}
