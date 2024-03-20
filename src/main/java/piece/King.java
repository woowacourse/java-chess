package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.KingStrategy;

public class King implements Piece {

    private final Color color;

    public King(Color color) {
        this.color = color;
    }

    @Override
    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate, boolean isAttack) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        KingStrategy kingStrategy = KingStrategy.getMoveStrategy(rowDifference, columnDifference);
        return kingStrategy.getDirection();
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
