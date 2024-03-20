package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.PawnStrategy;

public class Pawn {

    private boolean isBlack;

    public List<Integer> getPath(Coordinate coordinate, Coordinate nextCoordinate) {
        int rowMinus = coordinate.checkRow(nextCoordinate);
        int columnMinus = coordinate.checkColumn(nextCoordinate);

        List<Integer> pawnStrategy = PawnStrategy.getMoveStrategy(rowMinus, columnMinus);
        validate(coordinate, rowMinus);
        return pawnStrategy;
    }

    private void validate(Coordinate coordinate, int rowMinus) {
        if (coordinate.getRowValue() != getFirstPosition() && Math.abs(rowMinus) == 2) {
            throw new IllegalArgumentException("폰은 처음에만 2칸을 이동할 수 있습니다.");
        }
    }

    private int getFirstPosition() {
        if (isBlack) {
            return 1;
        }
        return 6;
    }
}
