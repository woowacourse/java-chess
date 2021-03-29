package chess.domain.piece.strategy;

import chess.domain.position.Direction;

public class BishopMoveStrategy extends DefaultMoveStrategy {
    public BishopMoveStrategy() {
        super(Direction.diagonalDirection());
    }
}
