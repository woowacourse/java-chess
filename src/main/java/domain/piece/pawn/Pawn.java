package domain.piece.pawn;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.PieceStrategy;
import java.util.Set;

public abstract class Pawn extends ChessPieceBase {

    private final PieceStrategy pieceStrategy;

    public Pawn(Color color, Set<Direction> directions) {
        super(color);
        this.pieceStrategy = new PieceStrategy(directions);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);
        validateDifference(rowDifference, columnDifference);

        if (canAttack) {
            pieceStrategy.findDirection(rowDifference, columnDifference);
        }
        return pieceStrategy.findDirection(divideValueByAbs(rowDifference), divideValueByAbs(columnDifference));
    }

    private int divideValueByAbs(int value) {
        if (value == 0) {
            return 0;
        }
        return value / Math.abs(value);
    }

    private void validateDifference(int rowDifference, int columnDifference) {
        if (Math.abs(rowDifference) + Math.abs(columnDifference) > 2) {
            throw new IllegalArgumentException("폰에게 가능한 방향이 없습니다.");
        }
    }

    public abstract boolean isFirstPosition(Coordinate coordinate);
}
