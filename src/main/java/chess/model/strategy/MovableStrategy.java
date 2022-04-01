package chess.model.strategy;

import chess.model.board.Square;
import chess.model.strategy.move.MoveType;

public interface MovableStrategy {
    boolean movable(Square source, Square target, MoveType moveType);
}
