package chess.domain.piece;

import chess.domain.position.Position;

public interface MoveStrategy {
    boolean movable(Position source, Position target);
}
