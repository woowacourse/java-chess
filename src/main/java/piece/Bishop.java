package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.BishopStrategy;

public class Bishop implements Piece {

    private final boolean isBlack;

    public Bishop(boolean isBlack) {
        this.isBlack = isBlack;
    }

    @Override
    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate, boolean canAttack) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        BishopStrategy bishopStrategy = BishopStrategy.getMoveStrategy(rowDifference, columnDifference);
        return bishopStrategy.getDirection();
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
