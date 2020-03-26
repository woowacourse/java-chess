package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;
import domain.team.Team;

public class Rook extends Piece {
    private static final String INITIAL = "R";

    public Rook(Team team, Point point) {
        super(INITIAL, team, point);
    }

    @Override
    public Piece move(Point afterPoint) {
        return new Rook(getTeam(), afterPoint);
    }

    @Override
    public void canMove(Direction direction) {
        if (!direction.isStraight()) {
            throw new CanNotMoveException();
        }
    }
}
