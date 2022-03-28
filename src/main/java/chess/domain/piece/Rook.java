package chess.domain.piece;

import chess.domain.LocationDiff;
import chess.domain.Direction;

public class Rook extends Piece{
    private static final double SCORE = 5;

    public Rook(Team team) {
        super(team, Name.ROOK);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return Direction.isRookDirection(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
