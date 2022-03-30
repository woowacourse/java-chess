package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.position.Position;

@FunctionalInterface
public interface MovingStrategy {

    boolean canMove(Board board, Position source, Position target);
}
