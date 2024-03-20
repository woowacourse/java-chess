package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Piece {
    public Knight(Team team) {
        super(team);
    }

    @Override
    public boolean canMoveTo(Position start, Position destination) {
        //TODO: 나이트 움직임 전략 구현 필요
        return false;
    }
}
