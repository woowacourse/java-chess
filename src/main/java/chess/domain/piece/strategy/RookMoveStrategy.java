package chess.domain.piece.strategy;

import chess.domain.postion.Position;

public class RookMoveStrategy implements MoveStrategy{
    @Override
    public void isMovable(Position source, Position target) {

        if(source.equals(target)) {
            throw new IllegalArgumentException("source와 target은 같을 수 없습니다.");
        }

        if(!source.isSameRank(target) || !source.isSameFile(target)) {
            throw new IllegalArgumentException("target은 source의 상하좌우에 있어야 합니다.");
        }
    }
}
