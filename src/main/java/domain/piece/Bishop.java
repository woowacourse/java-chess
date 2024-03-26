package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.DiagonalDirection;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;

public class Bishop extends ChessPieceBase {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        return DiagonalDirection.getDirection(rowDifference, columnDifference);
    }
}
