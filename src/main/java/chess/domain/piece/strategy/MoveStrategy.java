package chess.domain.piece.strategy;

import chess.domain.order.MoveRoute;

public interface MoveStrategy {
    boolean canMove(MoveRoute moveRoute);
}
