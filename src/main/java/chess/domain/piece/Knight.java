package chess.domain.piece;

import chess.domain.LocationDiff;
import chess.domain.state.Direction;

public class Knight extends Piece{
    private static final double SCORE = 2.5;

    public Knight(Team team) {
        super(team, Name.KNIGHT);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return Direction.isKnightDirection(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return locationDiff.computeDistance() == 1;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
