package chess.domain.piece.strategy;

import chess.domain.Position;

public class CrossMoveStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return fromPosition.isSameFile(toPosition) || fromPosition.isSameRank(toPosition);
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }
}
