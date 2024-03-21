package domain.piece;

import domain.coordinate.Coordinate;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.QueenStrategy;
import java.util.List;

public class Queen extends ChessPieceBase {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public List<Integer> getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        QueenStrategy queenStrategy = QueenStrategy.getMoveStrategy(rowDifference, columnDifference);
        return queenStrategy.getDirection();
    }
}
