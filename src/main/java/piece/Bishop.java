package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.BishopStrategy;

public class Bishop implements Piece {

    private final Color color;

    public Bishop(Color color) {
        this.color = color;
    }

    @Override
    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate, boolean canAttack) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        BishopStrategy bishopStrategy = BishopStrategy.getMoveStrategy(rowDifference, columnDifference);
        return bishopStrategy.getDirection();
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
