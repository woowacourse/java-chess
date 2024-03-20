package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends Piece {
    public Rook(Team team) {
        super(team);
    }

    @Override
    public boolean canMoveTo(Position start, Position destination) {
        //TODO: 룩 움직임 전략 구현 필요
        return false;
    }
}
