package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Distance;
import chess.model.position.File;
import chess.model.position.Rank;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {
    private static final int DISPLACEMENT = 1;
    private static final int INITIAL_SPECIAL_DISPLACEMENT = 2;
    private static final List<ChessPosition> INITIAL_WHITE_POSITION = Arrays.stream(File.values())
            .map(file -> new ChessPosition(file, Rank.TWO))
            .toList();
    private static final List<ChessPosition> INITIAL_BLACK_POSITION = Arrays.stream(File.values())
            .map(file -> new ChessPosition(file, Rank.SEVEN))
            .toList();

    public Pawn(final Side side) {
        super(side);
    }

    @Override
    public List<ChessPosition> findPath(
            final ChessPosition source, final ChessPosition target, final Piece targetPiece
    ) {
        checkValidTargetPiece(targetPiece);
        final Distance distance = target.calculateDistance(source);
        validateForwardPath(source, targetPiece, distance);
        if (canCrossMove(source, distance) || canDiagonalMove(targetPiece, distance)) {
            return distance.findPath(source);
        }
        throw new IllegalStateException("폰은 해당 경로로 이동할 수 없습니다.");
    }

    private void validateForwardPath(
            final ChessPosition source, final Piece targetPiece, final Distance distance
    ) {
        if (!targetPiece.isEmpty() && canCrossMove(source, distance)) {
            throw new IllegalArgumentException("타겟 위치에 기물이 존재하여 전진할 수 없습니다.");
        }
    }

    private boolean canCrossMove(final ChessPosition source, final Distance distance) {
        if (isPawnInitialPosition(source)) {
            return canMoveForwardWith(distance, DISPLACEMENT) ||
                    canMoveForwardWith(distance, INITIAL_SPECIAL_DISPLACEMENT);
        }
        return canMoveForwardWith(distance, DISPLACEMENT);
    }

    private boolean isPawnInitialPosition(final ChessPosition source) {
        if (side.isWhite()) {
            return INITIAL_WHITE_POSITION.contains(source);
        }
        if (side.isBlack()) {
            return INITIAL_BLACK_POSITION.contains(source);
        }
        throw new IllegalStateException("Source 위치가 비어있습니다.");
    }

    private boolean canDiagonalMove(final Piece targetPiece, final Distance distance) {
        return isPossibleDiagonal(distance)
                && !targetPiece.isEmpty()
                && !isSameSide(targetPiece);
    }

    private boolean canMoveForwardWith(final Distance distance, final int displacement) {
        return distance.isForward(side) && distance.hasSame(displacement);
    }

    private boolean isPossibleDiagonal(final Distance distance) {
        return distance.isDiagonalMovement() && distance.hasSame(DISPLACEMENT);
    }
}
