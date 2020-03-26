package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;
import domain.team.Team;

public class Bishop extends Piece {
    private static final String INITIAL = "B";

    public Bishop(Team team, Point point) {
        super(INITIAL, team, point);
    }

    @Override
    public Piece move(Point afterPoint) {
        return new Bishop(getTeam(), afterPoint);
    }

    @Override
    public void canMove(Direction direction) {
        if (!direction.isDiagonal()) {
            throw new CanNotMoveException("비숍은 대각선으로만 움직일 수 있습니다.");
        }
    }
}
