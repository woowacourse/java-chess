package chess.domain.piece;

import chess.domain.location.LocationDiff;
import chess.domain.location.Direction;

public class Bishop extends Piece {
    private static final double SCORE = 3;

    public Bishop(Team team) {
        super(team, Name.BISHOP);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return Direction.isBishopDirection(direction);
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
