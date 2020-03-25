package chess.domain.MovableStrategy;

import chess.domain.position.Position;

public interface MovableStrategy {
    boolean canMove(Position source, Position target);

    boolean canLeap(Position source, Position target);
}
