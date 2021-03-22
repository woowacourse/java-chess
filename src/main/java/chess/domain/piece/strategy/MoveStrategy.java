package chess.domain.piece.strategy;

import chess.domain.position.Position;

@FunctionalInterface
public interface MoveStrategy {

    boolean canMove(Position from, Position to);
}
