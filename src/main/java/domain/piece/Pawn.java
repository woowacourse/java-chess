package domain.piece;

import domain.coordinate.Coordinate;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.pawn.PawnAttackStrategy;
import domain.piece.strategy.pawn.PawnStrategy;
import java.util.List;

public class Pawn extends ChessPieceBase {

    private static final int BLACK_PAWN_FIRST_POSITION = 7;
    private static final int WHITE_PAWN_FIRST_POSITION = 2;
    private static final int MAX_PAWN_DISTANCE_DIFFERENCE = 2;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public List<Integer> getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        if (canAttack) {
            return getAttackDirection(start, rowDifference, columnDifference);
        }
        return getMoveDirection(start, rowDifference, columnDifference);
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
        validateCanMovePawn(coordinate, rowDifference);
        pawnStrategy.validateMoveBackward(color);
    }

    private void validateAttack(PawnAttackStrategy pawnStrategy, Coordinate coordinate, int rowDifference) {
        validateCanMovePawn(coordinate, rowDifference);
        pawnStrategy.validateMoveBackward(color);
    }

    private void validateCanMovePawn(Coordinate coordinate, int rowDifference) {
        if (!isFirstPosition(coordinate) && Math.abs(rowDifference) == MAX_PAWN_DISTANCE_DIFFERENCE) {
            throw new IllegalArgumentException("폰은 처음에만 2칸을 이동할 수 있습니다.");
        }
    }

    private boolean isFirstPosition(Coordinate coordinate) {
        if (isBlack()) {
            return coordinate.isSameRowPosition(BLACK_PAWN_FIRST_POSITION);
        }
        return coordinate.isSameRowPosition(WHITE_PAWN_FIRST_POSITION);
    }
}
