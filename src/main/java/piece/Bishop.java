package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.BishopStrategy;

public class Bishop {

    private final boolean isBlack;

    public Bishop(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public List<Integer> getPath(Coordinate coordinate, Coordinate nextCoordinate) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        BishopStrategy bishopStrategy = BishopStrategy.getMoveStrategy(rowDifference, columnDifference);
        return bishopStrategy.getDirection();
    }
}
