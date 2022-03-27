package chess.domain.piece.strategy;

import chess.domain.postion.Position;

public class RookMoveStrategy extends AbstractCommonMoveStrategy {
    @Override
    public void isMovable(Position source, Position target) {
        validateSamePosition(source, target);
        validateTopBottomRightLeft(source, target);
    }
}
