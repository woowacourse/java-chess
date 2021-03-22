package chess.domain.piece.strategy;

import chess.domain.position.Position;

public class KnightStrategy implements MoveStrategy {

    @Override
    public boolean canMove(Position from, Position to) {
        return from.isKnightPath(to);
    }
}
