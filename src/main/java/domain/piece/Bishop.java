package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.PieceStrategy;

public class Bishop extends ChessPieceBase {

    private final PieceStrategy pieceStrategy;

    public Bishop(Color color) {
        super(color);
        this.pieceStrategy = new PieceStrategy(Direction.DIAGONAL_DIRECTION);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        return pieceStrategy.findDirection(divideValueByAbs(rowDifference), divideValueByAbs(columnDifference));
    }

    private int divideValueByAbs(int value) {
        if (value == 0) {
            return 0;
        }
        return value / Math.abs(value);
    }
}
