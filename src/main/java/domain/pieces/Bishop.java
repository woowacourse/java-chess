package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.coordinate.Direction;
import domain.coordinate.Coordinate;
import domain.team.Team;

public class Bishop extends Piece {

    public Bishop(Team team, Coordinate coordinate) {
        super(PieceType.BISHOP, team, coordinate);
    }

    @Override
    public Piece move(Coordinate afterCoordinate) {
        return new Bishop(getTeam(), afterCoordinate);
    }

    @Override
    public void validateMoveDirection(Direction direction) {
        if (direction.isNotDiagonal()) {
            throw new CanNotMoveException("비숍은 대각선으로만 움직일 수 있습니다.");
        }
    }
}
