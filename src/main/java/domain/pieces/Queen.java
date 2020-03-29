package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;
import domain.team.Team;

public class Queen extends Piece {
    private static final double SCORE = 9;
    private static final String INITIAL = "Q";

    public Queen(Team team, Point point) {
        super(INITIAL, team, point, SCORE);
    }

    @Override
    public Piece move(Point afterPoint) {
        return new Queen(getTeam(), afterPoint);
    }

    @Override
    public void validateMoveDirection(Direction direction) {
        if (direction.isNotLinearDirection()) {
            throw new CanNotMoveException("Queen은 일직선이나 대각선으로만 이동할 수 있습니다.");
        }
    }
}
