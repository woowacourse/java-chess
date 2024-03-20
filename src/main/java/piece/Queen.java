package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.QueenStrategy;

public class Queen {

    private final boolean isBlack;

    public Queen(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        QueenStrategy queenStrategy = QueenStrategy.getMoveStrategy(rowDifference, columnDifference);
        return queenStrategy.getDirection();
    }
}
