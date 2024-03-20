package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {
    public King(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination) {
        //TODO: 킹 움직임 전략 구현 필요
        return false;
    }
}
