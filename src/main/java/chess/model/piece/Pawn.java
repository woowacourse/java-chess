package chess.model.piece;

import chess.model.game.PawnValue;
import chess.model.game.PieceValue;
import chess.model.position.Position;
import chess.model.position.Movement;
import chess.model.position.Path;
import chess.model.position.Rank;

import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Pawn extends Piece {
    private static final Map<Side, Pawn> CACHE = Side.colors().stream()
            .collect(toMap(identity(), Pawn::new));

    private static final int DISPLACEMENT = 1;
    private static final int INITIAL_SPECIAL_DISPLACEMENT = 2;

    private Pawn(Side side) {
        super(side);
    }

    public static Pawn from(Side side) {
        return CACHE.get(side);
    }

    @Override
    public Path findPath(Position sourcePosition, Position targetPosition, Piece targetPiece) {
        Movement movement = targetPosition.calculateMovement(sourcePosition);
        validateForwardPath(sourcePosition, targetPiece, movement);
        if (canOrthogonalMove(sourcePosition, movement) || canDiagonalMove(targetPiece, movement)) {
            return Path.makeStraightPath(sourcePosition, movement);
        }
        return Path.empty();
    }

    private void validateForwardPath(Position source, Piece targetPiece, Movement movement) {
        if (!targetPiece.equals(Blank.INSTANCE) && canOrthogonalMove(source, movement)) {
            throw new IllegalArgumentException("타겟 위치에 기물이 존재하여 전진할 수 없습니다.");
        }
    }

    private boolean canOrthogonalMove(Position source, Movement movement) {
        if (isPawnInitialPosition(source)) {
            return canMoveForwardWith(movement, DISPLACEMENT) ||
                    canMoveForwardWith(movement, INITIAL_SPECIAL_DISPLACEMENT);
        }
        return canMoveForwardWith(movement, DISPLACEMENT);
    }

    private boolean isPawnInitialPosition(Position source) {
        if (isSameSide(Side.WHITE)) {
            return source.hasRank(Rank.TWO);
        }
        return source.hasRank(Rank.SEVEN);
    }

    private boolean canMoveForwardWith(Movement movement, int displacement) {
        boolean isUpperSide = isSameSide(Side.BLACK);
        return movement.isForward(isUpperSide) && movement.hasLengthOf(displacement);
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

    @Override
    public PieceValue value() {
        return new PawnValue(1, 0.5);
    }
}
