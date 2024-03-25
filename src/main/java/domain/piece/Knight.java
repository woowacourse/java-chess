package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.KnightStrategy;

public class Knight extends ChessPieceBase {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean isAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        KnightStrategy knightStrategy = KnightStrategy.getMoveStrategy(rowDifference, columnDifference);
        return knightStrategy.getDirection();
    }
}
