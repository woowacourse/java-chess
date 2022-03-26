package chess.domain.piece;

import chess.domain.LocationDiff;
import chess.domain.state.Direction;

public class King extends Piece{

    public King(Team team) {
        super(team, Name.KING);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return Direction.isKingDirection(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return locationDiff.computeDistance() == 1;
    }
}
