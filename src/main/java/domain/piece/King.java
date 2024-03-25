package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.KingStrategy;

public class King extends ChessPieceBase {

    public King(Color color) {
        super(color);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        KingStrategy kingStrategy = KingStrategy.getMoveStrategy(rowDifference, columnDifference);
        return kingStrategy.getDirection();
    }
}
