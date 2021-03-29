package chess.domain.piece.strategy;

import chess.domain.piece.info.Position;

public class BishopMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(Position source, Position target) {
        if (source.isDiagonal(target)) {
            return true;
        }
        return false;
    }
}
