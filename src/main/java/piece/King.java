package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.KingStrategy;

public class King implements Piece {

    private final boolean isBlack;

    public King(boolean isBlack) {
        this.isBlack = isBlack;
    }

    @Override
    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate, boolean isAttack) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        KingStrategy kingStrategy = KingStrategy.getMoveStrategy(rowDifference, columnDifference);
        return kingStrategy.getDirection();
    }

    @Override
    public boolean isSameColor(boolean isBlack) {
        return this.isBlack == isBlack;
    }
}
