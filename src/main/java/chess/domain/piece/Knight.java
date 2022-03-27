package chess.domain.piece;

import chess.domain.LocationDiff;
import chess.domain.state.Direction;

public class Knight extends Piece{
    private static final double SCORE = 2.5;
    public static final int MOVABLE_DISTANCE = 1;

    public Knight(Team team) {
        super(team, Name.KNIGHT);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return Direction.isKnightDirection(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return locationDiff.computeDistance() == MOVABLE_DISTANCE;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
