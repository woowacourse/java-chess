package chess.domain.piece;

import chess.domain.LocationDiff;
import chess.domain.state.Direction;

public class EmptyPiece extends Piece{

    public EmptyPiece() {
        super(Team.NONE, Name.NONE);
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
