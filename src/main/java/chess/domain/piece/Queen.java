package chess.domain.piece;

import chess.domain.location.LocationDiff;
import chess.domain.location.Direction;

public class Queen extends Piece{
    private static final double SCORE = 9;

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

    @Override
    public double getScore() {
        return SCORE;
    }
}
