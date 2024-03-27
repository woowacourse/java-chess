package chess.domain.piece.strategy;

import chess.domain.square.Square;

public class DiagonalStrategy implements MoveStrategy {

    @Override
    public boolean canMove(final Square source, final Square target) {
        return source.isDiagonal(target);
    }
}
