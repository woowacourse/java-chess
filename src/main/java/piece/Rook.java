package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.RookStrategy;

public class Rook implements Piece {

    private final boolean isBlack;

    public Rook(boolean isBlack) {
        this.isBlack = isBlack;
    }

    @Override
    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate, boolean isAttack) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        RookStrategy rookStrategy = RookStrategy.getMoveStrategy(rowDifference, columnDifference);
        return rookStrategy.getDirection();
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
