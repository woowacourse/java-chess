package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.QueenStrategy;

public class Queen extends ChessPieceBase {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        QueenStrategy queenStrategy = QueenStrategy.getMoveStrategy(rowDifference, columnDifference);
        return queenStrategy.getDirection();
    }
}
