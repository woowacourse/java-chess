package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;
import domain.team.Team;

public class Bishop extends Piece {
    private static final double SCORE = 3;
    private static final String INITIAL = "B";

    public Bishop(Team team, Point point) {
        super(INITIAL, team, point, SCORE);
    }

    @Override
    public Piece move(Point afterPoint) {
        return new Bishop(getTeam(), afterPoint);
    }

    @Override
    public void canMove(Direction direction) {
        if (direction.isNotDiagonal()) {
            throw new CanNotMoveException("비숍은 대각선으로만 움직일 수 있습니다.");
        }
    }
}
