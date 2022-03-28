package chess.domain.piece.strategy;

import chess.domain.Position;

public class BishopMoveStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return new DiagonalMoveStrategy().isMovable(fromPosition, toPosition);
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }
}
