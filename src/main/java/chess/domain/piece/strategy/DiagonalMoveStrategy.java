package chess.domain.piece.strategy;

import chess.domain.square.Square;

public class DiagonalMoveStrategy implements MoveStrategy {

    private boolean movable(final Square current, final Square destination) {
        return false;
    }

    @Override
    public void move(final Square current, final Square destination) {
        current.isDiagonal(destination);
    }
}
