package chess.domain.piece.strategy;

import chess.domain.square.Square;

public class StraightMoveStrategy implements MoveStrategy {

    public boolean movable(final Square current, final Square destination) {
        return false;
    }

    @Override
    public boolean canMove(final Square current, final Square destination) {

        return false;
    }
}
