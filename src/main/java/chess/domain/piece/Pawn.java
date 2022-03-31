package chess.domain.piece;

import static chess.domain.board.Direction.D;

import chess.domain.board.Direction;
import chess.domain.board.LocationDiff;

public class Pawn extends Piece {
    private static final double SCORE = 1;

    public Pawn(Team team) {
        super(team, Name.PAWN);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return PawnDirectionFactory.getDirections(team).contains(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        Direction direction = locationDiff.computeDirection();
        int distance = locationDiff.computeDistance();

        if ((direction == D || direction == Direction.U) && isFirst()) {
            return distance <= 2;
        }
        return distance <= 1;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
