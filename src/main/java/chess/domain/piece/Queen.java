package chess.domain.piece;

import chess.domain.LocationDiff;
import chess.domain.state.Direction;

public class Queen extends Piece{

    public Queen(Team team) {
        super(team, Name.QUEEN);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return Direction.isQueenDirection(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return true;
    }
}
