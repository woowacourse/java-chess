package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Movement;
import chess.model.position.Rank;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class Pawn extends Piece {
    private static final Map<Side, Pawn> CACHE = Arrays.stream(Side.values())
            .collect(Collectors.toMap(identity(), Pawn::new));

    private static final int DISPLACEMENT = 1;
    private static final int INITIAL_SPECIAL_DISPLACEMENT = 2;

    private Pawn(Side side) {
        super(side);
    }

    public static Pawn from(Side side) {
        return CACHE.get(side);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "p";
        }
        return "P";
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition sourcePosition, ChessPosition targetPosition, Piece targetPiece) {
        checkValidTargetPiece(targetPiece);
        Movement movement = targetPosition.calculateMovement(sourcePosition);
        validateForwardPath(sourcePosition, targetPiece, movement);
        if (canOrthogonalMove(sourcePosition, movement) || canDiagonalMove(targetPiece, movement)) {
            return movement.findStraightPath(sourcePosition);
        }
        return List.of();
    }

    private void validateForwardPath(ChessPosition source, Piece targetPiece, Movement movement) {
        if (!targetPiece.equals(Blank.INSTANCE) && canOrthogonalMove(source, movement)) {
            throw new IllegalArgumentException("타겟 위치에 기물이 존재하여 전진할 수 없습니다.");
        }
    }

    private boolean canOrthogonalMove(ChessPosition source, Movement movement) {
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
        if (targetPiece.equals(Blank.INSTANCE)) {
            return false;
        }
        if (isSameSide(targetPiece)) {
            return false;
        }
        return isPossibleDiagonal(movement);
    }

    private boolean isPossibleDiagonal(Movement movement) {
        return movement.isDiagonal() && movement.hasLengthOf(DISPLACEMENT);
    }
}
