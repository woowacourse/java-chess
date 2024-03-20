package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.KnightStrategy;

public class Knight implements Piece {

    private final boolean isBlack;

    public Knight(boolean isBlack) {
        this.isBlack = isBlack;
    }

    @Override
    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate, boolean isAttack) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        KnightStrategy knightStrategy = KnightStrategy.getMoveStrategy(rowDifference, columnDifference);
        return knightStrategy.getDirection();
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
