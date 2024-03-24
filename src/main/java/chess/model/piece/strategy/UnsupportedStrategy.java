package chess.model.piece.strategy;

import chess.model.position.Movement;

public class UnsupportedStrategy implements PieceStrategy {
    @Override
    public boolean canMove(Movement movement) {
        throw new UnsupportedOperationException("정의되지 않은 전략 패턴입니다.");
    }
}
