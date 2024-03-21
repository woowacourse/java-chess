package domain.piece;

import domain.coordinate.Coordinate;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.BishopStrategy;
import java.util.List;

public class Bishop extends ChessPieceBase {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public List<Integer> getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        BishopStrategy bishopStrategy = BishopStrategy.getMoveStrategy(rowDifference, columnDifference);
        return bishopStrategy.getDirection();
    }
}
