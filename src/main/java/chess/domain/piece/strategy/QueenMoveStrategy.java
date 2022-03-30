package chess.domain.piece.strategy;

import chess.domain.position.Position;

public class QueenMoveStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return new CrossMoveStrategy().isMovable(fromPosition, toPosition)
            || new DiagonalMoveStrategy().isMovable(fromPosition, toPosition);
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }
}
