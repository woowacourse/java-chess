package chess.domain.chesspiece;

import chess.domain.position.Position;

import java.util.List;

import static chess.domain.chesspiece.Team.NOTHING;
import static chess.domain.chesspiece.Role.EMPTY;

public class Empty extends Piece {

    public Empty() {
        super(NOTHING);
    }

    @Override
    public List<Position> getRoute(Position source, Position target) {
        throw new IllegalArgumentException("해당 공간은 기물이 존재하지 않습니다.");
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
    }

    @Override
    public Role getRole() {
        return EMPTY;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
