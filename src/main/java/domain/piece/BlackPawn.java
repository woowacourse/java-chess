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
        boolean isInitialPawn = isInitialPawn(start);

        return getBlackPawnDirection(rowDifference, columnDifference, isInitialPawn);
    }

    private Direction getBlackPawnDirection(int rowDifference, int columnDifference, boolean isInitialPawn) {
        if (rowDifference == 1 && Math.abs(columnDifference) == 1) {
            return DiagonalDirection.getDirection(rowDifference, columnDifference);
        }
        if (canMoveDown(rowDifference, columnDifference, isInitialPawn)) {
            return StraightDirection.DOWN;
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }

    private boolean canMoveDown(int rowDifference, int columnDifference, boolean isInitialPawn) {
        if (columnDifference != 0) {
            return false;
        }
        if (rowDifference == 2) {
            return isInitialPawn;
        }
        return rowDifference == 1;
    }

    public boolean isInitialPawn(Coordinate start) {
        return start.hasSameRowPosition(Position.of(INITIAL_ROW));
    }
}
