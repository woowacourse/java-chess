package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.QueenStrategy;

public class Queen implements Piece {

    private final boolean isBlack;

    public Queen(boolean isBlack) {
        this.isBlack = isBlack;
    }

    @Override
    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate, boolean isAttack) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        QueenStrategy queenStrategy = QueenStrategy.getMoveStrategy(rowDifference, columnDifference);
        return queenStrategy.getDirection();
    }

    @Override
    public boolean isSameColor(boolean isBlack) {
        return this.isBlack == isBlack;
    }

    @Override
    public boolean isBlack() {
        return isBlack;
    }
}
