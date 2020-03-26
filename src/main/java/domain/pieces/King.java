package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;
import domain.team.Team;

public class King extends Piece {
    private static final String INITIAL = "K";

    public King(Team team, Point point) {
        super(INITIAL, team, point);
    }

    @Override
    public Piece move(Point afterPoint) {
        return new King(getTeam(), afterPoint);
    }

    @Override
    public void canMove(Direction direction) {
        if (direction.isElse()) {
            throw new CanNotMoveException();
        }
    }
}
