package chess.domain.piece;

import chess.domain.location.LocationDiff;
import chess.domain.state.Direction;

public class Pawn extends Piece {
    private static final double SCORE = 1;
    public static final int FIRST_TURN_MOVABLE_DISTANCE = 2;
    public static final int MOVABLE_DISTANCE = 1;

    public Pawn(Team team) {
        super(team, Name.PAWN);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        if (isBlack()) {
            return Direction.isBlackPawnDirection(direction);
        }
        return Direction.isWhitePawnDirection(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        Direction direction = locationDiff.computeDirection();
        int distance = locationDiff.computeDistance();

        if ((direction == Direction.D || direction == Direction.U) && isFirst()) {
            return distance <= FIRST_TURN_MOVABLE_DISTANCE;
        }
        return distance <= MOVABLE_DISTANCE;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
