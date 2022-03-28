package chess.domain.piece;

import chess.domain.LocationDiff;
import chess.domain.Direction;

public class EmptyPiece extends Piece{
    private static final double SCORE = 0;

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

    @Override
    public double getScore() {
        return SCORE;
    }
}
