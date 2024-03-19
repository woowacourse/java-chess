package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(Team team) {
        super(team);
    }

    @Override
    public boolean canMoveTo(Position target) {
        //TODO: 비숍 움직임 전략 구현 필요
        return false;
    }
}
