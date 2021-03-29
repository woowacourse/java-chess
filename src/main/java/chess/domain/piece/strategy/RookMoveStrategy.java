package chess.domain.piece.strategy;

import chess.domain.position.Direction;

public class RookMoveStrategy extends DefaultMoveStrategy {
    public RookMoveStrategy() {
        super(Direction.linearDirection());
    }
}
