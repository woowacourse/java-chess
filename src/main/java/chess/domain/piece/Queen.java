package chess.domain.piece;

import chess.domain.LocationDiff;
import chess.domain.Direction;

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
