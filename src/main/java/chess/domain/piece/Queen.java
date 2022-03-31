package chess.domain.piece;

import chess.domain.board.LocationDiff;
import chess.domain.board.Direction;

public class Queen extends Piece{
    private static final double SCORE = 9;

    public Queen(Team team) {
        super(team, Name.QUEEN);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
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
