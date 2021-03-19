package chess.domain.piece.strategy;

import chess.domain.position.Direction;

public class QueenMoveStrategy extends DefaultMoveStrategy {
    public QueenMoveStrategy() {
        super(Direction.everyDirection());
    }
}
