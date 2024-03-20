package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.KnightStrategy;

public class Knight {

    private final boolean isBlack;

    public Knight(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        KnightStrategy knightStrategy = KnightStrategy.getMoveStrategy(rowDifference, columnDifference);
        return knightStrategy.getDirection();
    }
}
