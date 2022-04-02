package chess.domain.piece.strategy;

import chess.domain.postion.Position;

public abstract class AbstractCommonMoveStrategy implements MoveStrategy {

    protected void validateSamePosition(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("기물은 제자리에 머무를 수 없습니다.");
        }
    }
}
