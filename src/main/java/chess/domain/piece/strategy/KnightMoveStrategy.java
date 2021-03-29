package chess.domain.piece.strategy;

import chess.domain.position.Direction;

public class KnightMoveStrategy extends DefaultMoveStrategy {
    public KnightMoveStrategy() {
        super(Direction.knightDirection());
    }
}
