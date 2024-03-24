package chess.model.piece.strategy;

import chess.model.position.Movement;

@FunctionalInterface
public interface PieceStrategy {
    boolean canMove(Movement movement);
}
