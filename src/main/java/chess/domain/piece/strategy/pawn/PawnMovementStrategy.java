package chess.domain.piece.strategy.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.strategy.AbstractPieceMovementStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PawnMovementStrategy extends AbstractPieceMovementStrategy {

    private final List<PawnMoveConstraint> constraints;

    public PawnMovementStrategy(final Color color, final List<PawnMoveConstraint> constraints) {
        super(color);
        this.constraints = new ArrayList<>(constraints);
    }

    public PawnMovementStrategy(final Color color, final PawnMoveConstraint... constraints) {
        this(color, Arrays.asList(constraints));
    }

    @Override
    protected void validateMoveWithNoAlly(final PiecePosition source,
                                          final PiecePosition destination,
                                          final Piece nullableEnemy) throws IllegalArgumentException {
        validateDefaultMove(source, destination);
        validateDiagonalKill(source, destination, nullableEnemy);
        validateVerticalMove(source, destination, nullableEnemy);

        for (final PawnMoveConstraint constraint : constraints) {
            constraint.validateConstraint(source, destination);
        }
    }

    private void validateDefaultMove(final PiecePosition source,
                                     final PiecePosition destination) {
        if (isHorizontal(source, destination)) {
            throw new IllegalArgumentException("폰은 수평으로 움직일 수 없습니다.");
        }
        if (!isUnitDistance(source, destination) && !isTwoVerticalMove(source, destination)) {
            throw new IllegalArgumentException("폰은 그렇게 움직일 수 없습니다.");
        }
    }

    private void validateDiagonalKill(final PiecePosition source,
                                      final PiecePosition destination,
                                      final Piece nullableEnemy) {
        if (nullableEnemy != null && isStraight(source, destination)) {
            throw new IllegalArgumentException("폰은 적이 있는 경우 직선으로 이동할 수 없습니다.");
        }
    }

    private void validateVerticalMove(final PiecePosition source,
                                      final PiecePosition destination,
                                      final Piece nullableEnemy) {
        if (nullableEnemy == null && isDiagonal(source, destination)) {
            throw new IllegalArgumentException("폰은 적이 없는 경우 대각선으로 이동할 수 없습니다.");
        }
    }
}
