package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;
import domain.team.Team;

public class Queen extends Piece {
    private static final String INITIAL = "Q";

    public Queen(Team team, Point point) {
        super(INITIAL, team, point);
    }

    @Override
    public Piece move(Point afterPoint) {
        return new Queen(getTeam(), afterPoint);
    }

    @Override
    public void canMove(Direction direction) {
        if (direction.isNotLinearDirection()) {
            throw new CanNotMoveException();
        }
    }
}
