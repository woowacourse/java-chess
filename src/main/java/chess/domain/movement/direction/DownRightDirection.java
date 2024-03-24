package chess.domain.movement.direction;


import chess.domain.position.Position;

public class DownRightDirection extends StraightDirection {

    public DownRightDirection(final int moveCount) {
        super(moveCount);
    }

    @Override
    Position next(final Position position) {
        if (position.isMinimumRank() || position.isMaximumFile()) {
            return position;
        }

        return position.right().down();
    }
}
