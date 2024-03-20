package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.pawn.PawnAttackStrategy;
import strategy.pawn.PawnStrategy;

public class Pawn {

    private static final int BLACK_PAWN_FIRST_POSITION = 1;
    private static final int WHITE_PAWN_FIRST_POSITION = 6;
    private static final int MAX_PAWN_DISTANCE_DIFFERENCE = 2;

    private final boolean isBlack;

    public Pawn(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate, boolean isAttack) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        if (isAttack) {
            return getAttackDirection(coordinate, rowDifference, columnDifference);
        }
        return getMoveDirection(coordinate, rowDifference, columnDifference);
    }

    private List<Integer> getAttackDirection(Coordinate coordinate, int rowDifference, int columnDifference) {
        PawnAttackStrategy pawnPath = PawnAttackStrategy.getMoveStrategy(rowDifference, columnDifference);
        validateAttack(pawnPath, coordinate, rowDifference);

        return pawnPath.getDirection();
    }

    private List<Integer> getMoveDirection(Coordinate coordinate, int rowDifference, int columnDifference) {
        PawnStrategy pawnPath = PawnStrategy.getMoveStrategy(rowDifference, columnDifference);
        validate(pawnPath, coordinate, rowDifference);

        return pawnPath.getDirection();
    }

    private void validate(PawnStrategy pawnStrategy, Coordinate coordinate, int rowDifference) {
        validateIsCanMovePawn(coordinate, rowDifference);
        pawnStrategy.validatePossibleStrategyColor(isBlack);
    }

    private void validateAttack(PawnAttackStrategy pawnStrategy, Coordinate coordinate, int rowDifference) {
        validateIsCanMovePawn(coordinate, rowDifference);
        pawnStrategy.validatePossibleStrategyColor(isBlack);
    }

    private void validateIsCanMovePawn(Coordinate coordinate, int rowDifference) {
        if (!isFirstPosition(coordinate.getRowValue()) && Math.abs(rowDifference) == MAX_PAWN_DISTANCE_DIFFERENCE) {
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
