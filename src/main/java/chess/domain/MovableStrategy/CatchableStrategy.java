package chess.domain.MovableStrategy;

import chess.domain.position.Position;

public interface CatchableStrategy {
    boolean canCatch(Position source, Position target);
}
