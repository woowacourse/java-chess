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

        try {
            return DiagonalDirection.getDirection(rowDifference, columnDifference);
        } catch (IllegalArgumentException e) {
            return StraightDirection.getDirection(rowDifference, columnDifference);
        }
    }

    @Override
    public boolean cantMove(Coordinate start, Coordinate destination) {
        Direction direction = getDirection(start, destination);
        return start.calculateDistanceToDestination(direction, destination) != 1;
    }
}
