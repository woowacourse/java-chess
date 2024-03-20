package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.KingStrategy;

public class King {

    private final boolean isBlack;

    public King(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        KingStrategy kingStrategy = KingStrategy.getMoveStrategy(rowDifference, columnDifference);
        return kingStrategy.getDirection();
    }
}
