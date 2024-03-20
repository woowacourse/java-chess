package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.KnightStrategy;

public class Knight implements Piece {

    private final Color color;

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate, boolean isAttack) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        KnightStrategy knightStrategy = KnightStrategy.getMoveStrategy(rowDifference, columnDifference);
        return knightStrategy.getDirection();
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
