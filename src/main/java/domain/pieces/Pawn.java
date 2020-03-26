package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;
import domain.team.Team;

public class Pawn extends Piece {
    private static final String INITIAL = "P";

    public Pawn(Team team, Point point) {
        super(INITIAL, team, point);
    }

    @Override
    public Piece move(Point afterPoint) {
        return new Pawn(getTeam(), afterPoint);
    }

    @Override
    public void canMove(Direction direction) {
        if (isBlack()) {
            if (direction != Direction.S) {
                throw new CanNotMoveException();
            }
            return;
        }
        if (isWhite()) {
            if (direction != Direction.N) {
                throw new CanNotMoveException();
            }
        }
    }
}
