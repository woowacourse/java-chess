package domain.piece;

import domain.coordinate.Coordinate;
import domain.coordinate.Position;
import domain.direction.DiagonalDirection;
import domain.direction.Direction;
import domain.direction.StraightDirection;
import domain.piece.base.ChessPieceBase;

public class BlackPawn extends ChessPieceBase {

    private static final int INITIAL_ROW = 1;

    public BlackPawn() {
        super(Color.BLACK);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        return getBlackPawnDirection(rowDifference, columnDifference);
    }

    private Direction getBlackPawnDirection(int rowDifference, int columnDifference) {
        if (rowDifference == 1 && Math.abs(columnDifference) == 1) {
            return DiagonalDirection.getDirection(rowDifference, columnDifference);
        }
        if ((rowDifference == 1 || rowDifference == 2) && Math.abs(columnDifference) == 0) {
            return StraightDirection.DOWN;
        }
        throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
    }

    @Override
    public boolean cantMove(Coordinate start, Coordinate destination) {
        Direction direction = getDirection(start, destination);
        int distance = start.calculateDistanceToDestination(direction, destination);

        if (isInitialPawn(start) && distance == 2) {
            return false;
        }
        return distance != 1;
    }

    private boolean isInitialPawn(Coordinate start) {
        return start.hasSameRowPosition(new Position(INITIAL_ROW));
    }
}
