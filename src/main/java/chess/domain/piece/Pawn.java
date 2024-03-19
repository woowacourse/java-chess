package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends Piece {
    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean canMoveTo(Position target) {
        //TODO: 폰 움직임 전략 구현 필요
        return false;
    }
}
