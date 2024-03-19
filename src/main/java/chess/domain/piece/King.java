package chess.domain.piece;

import chess.domain.position.Position;

public class King implements Piece {
    @Override
    public boolean canMoveTo(Position target) {
        //TODO: 킹 움직임 전략 구현 필요
        return false;
    }
}
