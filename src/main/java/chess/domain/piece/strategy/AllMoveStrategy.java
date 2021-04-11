package chess.domain.piece.strategy;

import chess.domain.piece.info.Position;

public abstract class AllMoveStrategy implements MoveStrategy {
    protected boolean isNotCrossOrDiagonal(Position source, Position target) {
        return !source.isCross(target) && !source.isDiagonal(target);
    }
}
