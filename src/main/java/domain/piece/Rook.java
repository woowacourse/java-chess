package domain.piece;

import domain.coordinate.Coordinate;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.RookStrategy;
import java.util.List;

public class Rook extends ChessPieceBase {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public List<Integer> getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        RookStrategy rookStrategy = RookStrategy.getMoveStrategy(rowDifference, columnDifference);
        return rookStrategy.getDirection();
    }
}
