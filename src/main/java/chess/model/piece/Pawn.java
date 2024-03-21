package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Distance;

import java.util.List;

public class Pawn extends Piece {
    private static final int DISPLACEMENT = 1;
    private static final int INITIAL_SPECIAL_DISPLACEMENT = 2;

    public Pawn(Side side) {
        super(side);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "p";
        }
        return "P";
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        checkValidTargetPiece(targetPiece);
        Distance distance = target.calculateDistance(source);
        validateForwardPath(source, targetPiece, distance);
        if (canCrossMove(source, distance) || canDiagonalMove(targetPiece, distance)) {
            return distance.findPath(source);
        }
        return List.of();
    }

    private void validateForwardPath(ChessPosition source, Piece targetPiece, Distance distance) {
        if (targetPiece != null && canCrossMove(source, distance)) {
            throw new IllegalArgumentException("타겟 위치에 기물이 존재하여 전진할 수 없습니다.");
        }
    }

    private boolean canDiagonalMove(Piece targetPiece, Distance distance) {
        return isPossibleDiagonal(distance) && targetPiece != null && !isSameSide(targetPiece);
    }

    private boolean isPossibleDiagonal(Distance distance) {
        return distance.isDiagonalMovement() && distance.hasSame(DISPLACEMENT);
    }

    private boolean canCrossMove(ChessPosition source, Distance distance) {
        if (source.isPawnInitialPosition(side)) {
            return canMoveForwardWith(distance, DISPLACEMENT) ||
                    canMoveForwardWith(distance, INITIAL_SPECIAL_DISPLACEMENT);
        }
        return canMoveForwardWith(distance, DISPLACEMENT);
    }

    private boolean canMoveForwardWith(Distance distance, int displacement) {
        return distance.isForward(side) && distance.hasSame(displacement);
    }
}
