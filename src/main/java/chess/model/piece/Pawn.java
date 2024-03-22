package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Movement;
import chess.model.position.Rank;

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
        Movement movement = target.calculateMovement(source);
        validateForwardPath(source, targetPiece, movement);
        if (canCrossMove(source, movement) || canDiagonalMove(targetPiece, movement)) {
            return movement.findStraightPath(source);
        }
        return List.of();
    }

    private void validateForwardPath(ChessPosition source, Piece targetPiece, Movement movement) {
        if (targetPiece != null && canCrossMove(source, movement)) {
            throw new IllegalArgumentException("타겟 위치에 기물이 존재하여 전진할 수 없습니다.");
        }
    }

    private boolean canCrossMove(ChessPosition source, Movement movement) {
        if (isPawnInitialPosition(source)) {
            return canMoveForwardWith(movement, DISPLACEMENT) ||
                    canMoveForwardWith(movement, INITIAL_SPECIAL_DISPLACEMENT);
        }
        return canMoveForwardWith(movement, DISPLACEMENT);
    }

    private boolean isPawnInitialPosition(ChessPosition source) {
        if (side.isWhite()) {
            return source.hasRank(Rank.TWO);
        }
        return source.hasRank(Rank.SEVEN);
    }

    private boolean canMoveForwardWith(Movement movement, int displacement) {
        return movement.isForward(side) && movement.hasLengthOf(displacement);
    }

    private boolean canDiagonalMove(Piece targetPiece, Movement movement) {
        return isPossibleDiagonal(movement) && targetPiece != null && !isSameSide(targetPiece);
    }

    private boolean isPossibleDiagonal(Movement movement) {
        return movement.isDiagonal() && movement.hasLengthOf(DISPLACEMENT);
    }
}
