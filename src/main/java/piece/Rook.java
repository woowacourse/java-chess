package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.RookStrategy;

public class Rook {

    private final boolean isBlack;

    public Rook(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        RookStrategy rookStrategy = RookStrategy.getMoveStrategy(rowDifference, columnDifference);
        return rookStrategy.getDirection();
    }
}
