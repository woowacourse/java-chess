package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;
import domain.team.Team;

public class Knight extends Piece {

    public Knight(Team team, Point point) {
        super(PieceType.KNIGHT, team, point);
    }

    @Override
    public Piece move(Point afterPoint) {
        return new Knight(getTeam(), afterPoint);
    }

    @Override
    public void validateMoveDirection(Direction direction) {
        if (direction != Direction.KNIGHT) {
            throw new CanNotMoveException("나이트는 1 * 2 혹은 2 * 1 로만 움직일 수 있습니다.");
        }
    }
}
