package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPieceBase;
import domain.piece.strategy.pawn.PawnAttackStrategy;
import domain.piece.strategy.pawn.PawnStrategy;

public class Pawn extends ChessPieceBase {

    private static final int BLACK_PAWN_FIRST_POSITION = 7;
    private static final int WHITE_PAWN_FIRST_POSITION = 2;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack) {
        int rowDifference = start.calculateRowDifference(destination);
        int columnDifference = start.calculateColumnDifference(destination);

        if (canAttack) {
            return getAttackDirection(rowDifference, columnDifference);
        }
        return getMoveDirection(rowDifference, columnDifference);
    }

    private Direction getAttackDirection(int rowDifference, int columnDifference) {
        PawnAttackStrategy pawnPath = PawnAttackStrategy.getMoveStrategy(rowDifference, columnDifference);
        validateAttack(pawnPath);

        return pawnPath.getDirection();
    }

    private Direction getMoveDirection(int rowDifference, int columnDifference) {
        PawnStrategy pawnPath = PawnStrategy.getMoveStrategy(rowDifference, columnDifference);
        validate(pawnPath);

        return pawnPath.getDirection();
    }

    private void validate(PawnStrategy pawnStrategy) {
        pawnStrategy.validateMoveBackward(color);
    }

    private void validateAttack(PawnAttackStrategy pawnStrategy) {
        pawnStrategy.validateMoveBackward(color);
    }

    public boolean isFirstPosition(Coordinate coordinate) {
        if (isBlack()) {
            return coordinate.isSameRowPosition(BLACK_PAWN_FIRST_POSITION);
        }
        return coordinate.isSameRowPosition(WHITE_PAWN_FIRST_POSITION);
    }
}
