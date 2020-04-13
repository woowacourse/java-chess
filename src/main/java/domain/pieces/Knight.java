package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.coordinate.Direction;
import domain.coordinate.Coordinate;
import domain.team.Team;

public class Knight extends Piece {

    public Knight(Team team, Coordinate coordinate) {
        super(PieceType.KNIGHT, team, coordinate);
    }

    @Override
    public Piece move(Coordinate afterCoordinate) {
        return new Knight(getTeam(), afterCoordinate);
    }

    @Override
    public void validateMoveDirection(Direction direction) {
        if (direction != Direction.KNIGHT) {
            throw new CanNotMoveException("나이트는 1 * 2 혹은 2 * 1 로만 움직일 수 있습니다.");
        }
    }
}
