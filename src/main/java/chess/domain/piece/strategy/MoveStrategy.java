package chess.domain.piece.strategy;

import chess.domain.piece.info.Position;

public interface MoveStrategy {
    boolean canMove(Position source, Position target);
}
