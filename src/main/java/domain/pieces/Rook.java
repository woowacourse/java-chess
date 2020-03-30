package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Point;
import domain.team.Team;

public class Rook extends Piece {

    public Rook(Team team, Point point) {
        super(PieceType.ROOK, team, point);
    }

    @Override
    public Piece move(Point afterPoint) {
        return new Rook(getTeam(), afterPoint);
    }

    @Override
    public void validateMoveDirection(Direction direction) {
        if (direction.isNotStraight()) {
            throw new CanNotMoveException("Rook은 일직선으로만 이동할 수 있습니다.");
        }
    }
}
