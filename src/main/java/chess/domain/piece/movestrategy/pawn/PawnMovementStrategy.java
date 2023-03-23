package chess.domain.piece.movestrategy.pawn;

import chess.domain.piece.Piece;
import chess.domain.piece.movestrategy.AbstractPieceMovementStrategy;
import chess.domain.piece.position.File;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;

public abstract class PawnMovementStrategy extends AbstractPieceMovementStrategy {

    private static final double PAWN_VALUE = 1;

    private final PiecePosition permitTwoMoveRankPosition;

    protected PawnMovementStrategy(final Rank permitTwoMoveRank) {
        this.permitTwoMoveRankPosition = PiecePosition.of(permitTwoMoveRank, File.from(File.MIN));
    }

    @Override
    public double judgeValue() {
        return PAWN_VALUE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    protected void validateMoveWithNoAlly(final PiecePosition source,
                                          final PiecePosition destination,
                                          final Piece nullableEnemy) {
        validateDefaultMove(source, destination);
        validateDiagonalKill(source, destination, nullableEnemy);
        validateVerticalMove(source, destination, nullableEnemy);
        validateAdditionalConstraint(source, destination);
    }

    protected abstract void validateAdditionalConstraint(final PiecePosition source,
                                                         final PiecePosition destination);

    private void validateDefaultMove(final PiecePosition source,
                                     final PiecePosition destination) {
        if (isHorizontal(source, destination)) {
            throw new IllegalArgumentException("폰은 수평으로 움직일 수 없습니다.");
        }
        if (isUnitDistance(source, destination)) {
            return;
        }
        if (!isTwoVerticalMove(source, destination)) {
            throw new IllegalArgumentException("폰은 그렇게 움직일 수 없습니다.");
        }
        if (permitTwoMoveRankPosition.rankInterval(source) != 0) {
            throw new IllegalArgumentException(
                    String.format("%s 랭크에서만 두 칸 이동할 수 있습니다.", permitTwoMoveRankPosition.rankValue())
            );
        }
    }

    private boolean isTwoVerticalMove(final PiecePosition source, final PiecePosition destination) {
        if (Math.abs(source.rankInterval(destination)) != 2) {
            return false;
        }
        return Math.abs(source.fileInterval(destination)) == 0;
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

    protected boolean isDestinationRelativelySouth(final PiecePosition source, final PiecePosition destination) {
        return source.rankInterval(destination) < 0;
    }

    protected boolean isDestinationRelativelyNorth(final PiecePosition source, final PiecePosition destination) {
        return source.rankInterval(destination) > 0;
    }
}
