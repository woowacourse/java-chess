package piece;

import coordinate.Coordinate;
import java.util.List;
import strategy.pawn.PawnAttackStrategy;
import strategy.pawn.PawnStrategy;

public class Pawn implements Piece {

    private static final int BLACK_PAWN_FIRST_POSITION = 1;
    private static final int WHITE_PAWN_FIRST_POSITION = 6;
    private static final int MAX_PAWN_DISTANCE_DIFFERENCE = 2;

    private final Color color;

    public Pawn(Color color) {
        this.color = color;
    }

    @Override
    public List<Integer> getDirection(Coordinate coordinate, Coordinate nextCoordinate, boolean canAttack) {
        int rowDifference = coordinate.checkRow(nextCoordinate);
        int columnDifference = coordinate.checkColumn(nextCoordinate);

        if (canAttack) {
            return getAttackDirection(coordinate, rowDifference, columnDifference);
        }
        return getMoveDirection(coordinate, rowDifference, columnDifference);
    }

    @Override
    public boolean isSameColor(Color currentTurn) {
        return this.color == currentTurn;
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

    // todo: validateMoveBackward
    private void validate(PawnStrategy pawnStrategy, Coordinate coordinate, int rowDifference) {
        validateIsCanMovePawn(coordinate, rowDifference);
        pawnStrategy.validatePossibleStrategyColor(color);
    }

    private void validateAttack(PawnAttackStrategy pawnStrategy, Coordinate coordinate, int rowDifference) {
        validateIsCanMovePawn(coordinate, rowDifference);
        pawnStrategy.validatePossibleStrategyColor(color);
    }

    // todo: validateCanMove ?
    private void validateIsCanMovePawn(Coordinate coordinate, int rowDifference) {
        if (!isFirstPosition(coordinate.getRowValue()) && Math.abs(rowDifference) == MAX_PAWN_DISTANCE_DIFFERENCE) {
            throw new IllegalArgumentException("폰은 처음에만 2칸을 이동할 수 있습니다.");
        }
    }

    // todo: validateIsCanMovePawn 대체?
//    private void validateCanMove(Coordinate coordinate, int rowDifference) {
//        if (isInitialPawn(coordinate) || Math.abs(rowDifference) == 1) {
//            return;
//        }
//        throw new IllegalArgumentException("폰은 처음에만 2칸을 이동할 수 있습니다.");
//    }

    /* todo: getter 제거
    public boolean isSameRow(Row row) {
        return this.row.equals(row);
    }
     */
//    private boolean isInitialPawn(Coordinate coordinate) {
//        return (coordinate.hasSameRow(new Row(1))  && color == Color.BLACK)
//                || (coordinate.hasSameRow(new Row(6)) && color == Color.WHITE);
//    }

    private boolean isFirstPosition(int position) {
        if (isBlack()) {
            return position == BLACK_PAWN_FIRST_POSITION;
        }
        return position == WHITE_PAWN_FIRST_POSITION;
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
