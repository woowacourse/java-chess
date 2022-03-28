package chess.domain.piece.strategy;

import chess.domain.Position;

public class DiagonalMoveStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        int height = fromPosition.getOrdinateDifference(toPosition);
        int width = fromPosition.getAbscissaDifference(toPosition);
        return Math.pow(height, 2) == Math.pow(width, 2);
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }
}
