package chess.domain.chessPiece;

import chess.domain.Position;

import java.util.List;

import static chess.domain.Team.NOTHING;
import static chess.domain.chessPiece.Role.EMPTY;

public class Empty extends Piece{

    public Empty() {
        super(NOTHING);
    }
    @Override
    public List<Position> getRoute(Position source, Position target) {
        return null;
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {}

    @Override
    public Role getRole() {
        return EMPTY;
    }
}
