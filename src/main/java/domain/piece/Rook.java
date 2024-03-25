package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.RookStrategy;

public class Rook extends ChessPieceBase {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        RookStrategy rookStrategy = RookStrategy.getMoveStrategy(rowDifference, columnDifference);
        return rookStrategy.getDirection();
    }
}
