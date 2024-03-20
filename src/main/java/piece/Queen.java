package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.QueenStrategy;

public class Queen implements Piece {

    private final Color color;

    public Queen(Color color) {
        this.color = color;
    }

    @Override
    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate, boolean isAttack) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        QueenStrategy queenStrategy = QueenStrategy.getMoveStrategy(rowDifference, columnDifference);
        return queenStrategy.getDirection();
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
