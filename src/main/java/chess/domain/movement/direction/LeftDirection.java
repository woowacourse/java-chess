package chess.domain.movement.direction;


import chess.domain.position.Position;

public class LeftDirection extends StraightDirection {

    public LeftDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMinimumFile()) {
            return position;
        }
        return position.left();
    }
}
