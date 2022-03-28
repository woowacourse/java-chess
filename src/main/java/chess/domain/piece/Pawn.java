package chess.domain.piece;

import chess.domain.LocationDiff;
import chess.domain.Direction;

public class Pawn extends Piece {
    private static final double SCORE = 1;

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
            return distance <= 2;
        }
        return distance <= 1;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
