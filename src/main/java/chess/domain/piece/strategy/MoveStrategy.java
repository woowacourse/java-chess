package chess.domain.piece.strategy;

import chess.domain.position.Position;

public interface MoveStrategy {
    boolean isMovable(Position fromPosition, Position toPosition);

    boolean isCatchable(Position fromPosition, Position toPosition);
}
