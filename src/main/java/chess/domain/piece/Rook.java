package chess.domain.piece;

import chess.domain.LocationDiff;
import chess.domain.state.Direction;

public class Rook extends Piece{

    public Rook(Team team) {
        super(team, Name.ROOK);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return false;
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return false;
    }
}
