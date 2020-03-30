package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.pieces.exceptions.CanNotReachException;
import domain.point.Direction;
import domain.point.Distance;
import domain.point.Point;
import domain.team.Team;

public class King extends Piece {

    public King(Team team, Point point) {
        super(PieceType.KING, team, point);
    }

    @Override
    public Piece move(Point afterPoint) {
        return new King(getTeam(), afterPoint);
    }

    @Override
    public void validateMoveDirection(Direction direction) {
        if (direction.isNotLinearDirection()) {
            throw new CanNotMoveException("움직일 수 없는 방향입니다.");
        }
    }

    @Override
    public void validateReach(Distance distance) {
        if (distance != Distance.ONE) {
            throw new CanNotReachException("킹은 한 칸만 움직일 수 있습니다.");
        }
    }
}
