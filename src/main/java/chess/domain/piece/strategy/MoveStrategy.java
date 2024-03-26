package chess.domain.piece.strategy;

import chess.domain.square.Square;

public interface MoveStrategy {

    boolean canMove(final Square source, final Square target);
}
