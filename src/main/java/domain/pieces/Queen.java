package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;
import domain.team.Team;

public class Queen extends Piece {

    public Queen(Team team, Point point) {
        super(PieceType.QUEEN, team, point);
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
