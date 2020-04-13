package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.coordinate.Direction;
import domain.coordinate.Coordinate;
import domain.team.Team;

public class Queen extends Piece {

    public Queen(Team team, Coordinate coordinate) {
        super(PieceType.QUEEN, team, coordinate);
    }

    @Override
    public Piece move(Coordinate afterCoordinate) {
        return new Queen(getTeam(), afterCoordinate);
    }

    @Override
    public void validateMoveDirection(Direction direction) {
        if (direction.isNotLinearDirection()) {
            throw new CanNotMoveException("Queen은 일직선이나 대각선으로만 이동할 수 있습니다.");
        }
    }
}
