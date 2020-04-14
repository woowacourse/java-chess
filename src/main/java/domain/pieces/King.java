package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.pieces.exceptions.CanNotReachException;
import domain.coordinate.Direction;
import domain.coordinate.Distance;
import domain.coordinate.Coordinate;
import domain.team.Team;

public class King extends Piece {

    public King(Team team, Coordinate coordinate) {
        super(PieceType.KING, team, coordinate);
    }

    @Override
    public Piece move(Coordinate afterCoordinate) {
        return new King(getTeam(), afterCoordinate);
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
