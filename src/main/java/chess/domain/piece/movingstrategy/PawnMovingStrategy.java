package chess.domain.piece.movingstrategy;

import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import chess.domain.position.YAxis;

public class PawnMovingStrategy extends MovingStrategy {

    @Override
    public boolean isAbleToMove(Position from, Position to, PieceColor pieceColor) {
        if (isInitialPosition(from, pieceColor)) {
            return isAbleToMoveOnInitialPosition(from, to, pieceColor);
        }

        return isAbleToMoveNotOnInitialPosition(from, to, pieceColor);
    }

    @Override
    boolean isPossibleStep(Position from, Position to) {
        return false; // TODO: 리팩토링
    }

    @Override
    boolean isPossibleDirection(Position from, Position to) {
        return false; // TODO: 리팩토링
    }

    private boolean isInitialPosition(Position from, PieceColor pieceColor) {
        if (pieceColor == PieceColor.WHITE) {
            return from.isSameYAxis(YAxis.TWO);
        }

        return from.isSameYAxis(YAxis.SEVEN);
    }

    private boolean isAbleToMoveOnInitialPosition(Position from, Position to, PieceColor pieceColor) {
        boolean inVerticalRange = from.isInVerticalRangeAndSameXAxis(to, 2);

        if (pieceColor == PieceColor.BLACK) {
            return from.isUpperThan(to) && inVerticalRange;
        }

        return from.isLowerThan(to) && inVerticalRange;
    }

    private boolean isAbleToMoveNotOnInitialPosition(Position from, Position to, PieceColor pieceColor) {
        boolean inVerticalRange = from.isInVerticalRangeAndSameXAxis(to, 1);

        if (pieceColor == PieceColor.BLACK) {
            return from.isUpperThan(to) && inVerticalRange;
        }

        return from.isLowerThan(to) && inVerticalRange;
    }

    @Override
    public boolean isAbleToAttack(Position from, Position to, PieceColor pieceColor) {
        boolean isDiagonalOneDistance = from.isOnDiagonal(to) && from.isInVerticalRange(to, 1);

        if (pieceColor == PieceColor.BLACK) {
            return isDiagonalOneDistance && from.isUpperThan(to);
        }

        return isDiagonalOneDistance && from.isLowerThan(to);
    }
}
