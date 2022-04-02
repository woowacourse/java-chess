package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.position.Position;

@FunctionalInterface
public interface MovingStrategy {

    void validateMove(Board board, Position sourcePosition, Position targetPosition);
}
