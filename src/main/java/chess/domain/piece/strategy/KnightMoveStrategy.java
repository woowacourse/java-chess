package chess.domain.piece.strategy;

import chess.domain.square.Square;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public boolean movable(final Square current, final Square destination) {
        return false;
    }

    @Override
    public void move(final Square current, final Square destination) {

    }
}
