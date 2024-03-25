package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.BishopStrategy;

public class Bishop extends ChessPieceBase {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        BishopStrategy bishopStrategy = BishopStrategy.getMoveStrategy(rowDifference, columnDifference);
        return bishopStrategy.getDirection();
    }
}
