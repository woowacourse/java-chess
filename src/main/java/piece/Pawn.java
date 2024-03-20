package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.PawnStrategy;

public class Pawn {

    private static final int BLACK_PAWN_FIRST_POSITION = 1;
    private static final int WHITE_PAWN_FIRST_POSITION = 6;
    private static final int MAX_PAWN_DISTANCE_DIFFERENCE = 2;

    private final boolean isBlack;

    public Pawn(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public List<Integer> getPath(Coordinate coordinate, Coordinate nextCoordinate) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        List<Integer> pawnPath = PawnStrategy.getMoveStrategy(rowDifference, columnDifference);
        validateIsCanMovePawn(coordinate, rowDifference);
        return pawnPath;
    }

    private void validateIsCanMovePawn(Coordinate coordinate, int rowDifference) {
        if (isFirstPosition(coordinate.getRowValue()) && Math.abs(rowDifference) == MAX_PAWN_DISTANCE_DIFFERENCE) {
            throw new IllegalArgumentException("폰은 처음에만 2칸을 이동할 수 있습니다.");
        }
    }

    private boolean isFirstPosition(int position) {
        if (isBlack) {
            return position == BLACK_PAWN_FIRST_POSITION;
        }
        return position == WHITE_PAWN_FIRST_POSITION;
    }
}
