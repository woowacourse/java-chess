package chess.domain.chessPiece;

import chess.domain.position.Position;

import java.util.List;

import static chess.domain.chessPiece.Team.NOTHING;
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
