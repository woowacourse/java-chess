package chess.domain.piece.strategy;

import chess.domain.postion.Position;

public class BishopMoveStrategy extends AbstractCommonMoveStrategy {
    @Override
    public void isMovable(Position source, Position target) {
        validateSamePosition(source, target);
        validateDiagonal(source, target);
    }
}
