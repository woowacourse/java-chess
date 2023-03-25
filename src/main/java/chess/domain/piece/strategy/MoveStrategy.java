package chess.domain.piece.strategy;

import chess.domain.position.Move;

public interface MoveStrategy {

    boolean canMove(Move move);
}
