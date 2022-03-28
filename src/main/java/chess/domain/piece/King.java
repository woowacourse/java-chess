package chess.domain.piece;

import chess.domain.location.LocationDiff;
import chess.domain.location.Direction;

public class King extends Piece{
    public static final int MOVABLE_DISTANCE = 1;
    private static final double SCORE = 0;


    public King(Team team) {
        super(team, Name.KING);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return Direction.isKingDirection(direction);
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
