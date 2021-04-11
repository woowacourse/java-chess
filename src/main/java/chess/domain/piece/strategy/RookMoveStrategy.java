package chess.domain.piece.strategy;

import chess.domain.piece.info.Position;

public class RookMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(Position source, Position target) {
        if (source.isCross(target)) {
            return true;
        }
        return false;
    }
}
