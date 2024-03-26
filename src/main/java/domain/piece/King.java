package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.DiagonalDirection;
import domain.direction.Direction;
import domain.direction.StraightDirection;
import domain.piece.base.ChessPieceBase;

public class King extends ChessPieceBase {

    public King(Color color) {
        super(color);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);
        Direction direction = getKingDirection(rowDifference, columnDifference);
        validateDistance(start, destination, direction);

        return direction;
    }

    private Direction getKingDirection(int rowDifference, int columnDifference) {
        if (DiagonalDirection.isDiagonal(rowDifference, columnDifference)) {
            return DiagonalDirection.getDirection(rowDifference, columnDifference);
        }
        return StraightDirection.getDirection(rowDifference, columnDifference);
    }

    private void validateDistance(Coordinate start, Coordinate destination, Direction direction) {
        if (start.calculateDistanceToDestination(direction, destination) != 1) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }
}
