package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.postion.Position;

public class BishopMoveStrategy implements MoveStrategy {
    @Override
    public void isMovable(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("source와 target은 같을 수 없습니다.");
        }

        if (!source.isDiagonal(target)) {
            throw new IllegalArgumentException("source와 target은 대각선이여야 합니다.");
        }
    }
}
