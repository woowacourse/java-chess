package chess.domain.piece;

import static chess.domain.position.direction.DirectionUtil.*;

import chess.domain.position.Position;
import chess.domain.position.YAxis;
import chess.domain.position.direction.DiagonalDirection;
import chess.domain.position.direction.Direction;

public class Pawn extends AbstractPiece {

    private static final int DEFAULT_MOVE_RANGE = 1;
    private static final int INITIAL_MOVE_RANGE = 2;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor, PieceType.PAWN);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        if (isInitialPosition(from, getPieceColor())) {
            return getMovableIfInitialPosition(from, to);
        }

        return getMovableIfNotInitialPosition(from, to);
    }

    @Override
    public boolean isAbleToAttack(Position from, Position to) {
        Direction direction = new DiagonalDirection();
        boolean isDiagonalOneDistance = direction.isOnDirection(from, to) && isInVerticalRange(from, to, 1);

        if (isPieceColor(PieceColor.BLACK)) {
            return isDiagonalOneDistance && from.isUpperThan(to);
        }

        return isDiagonalOneDistance && from.isLowerThan(to);
    }

    private boolean isInitialPosition(Position from, PieceColor pieceColor) {
        if (pieceColor == PieceColor.WHITE) {
            return from.isSameYAxis(YAxis.TWO);
        }

        return from.isSameYAxis(YAxis.SEVEN);
    }

    private boolean getMovableIfInitialPosition(Position from, Position to) {
        boolean inVerticalRange = from.isInVerticalRangeAndSameXAxis(to, INITIAL_MOVE_RANGE);

        if (isPieceColor(PieceColor.BLACK)) {
            return from.isUpperThan(to) && inVerticalRange;
        }

        return from.isLowerThan(to) && inVerticalRange;
    }

    private boolean getMovableIfNotInitialPosition(Position from, Position to) {
        boolean inVerticalRange = from.isInVerticalRangeAndSameXAxis(to, DEFAULT_MOVE_RANGE);

        if (isPieceColor(PieceColor.BLACK)) {
            return from.isUpperThan(to) && inVerticalRange;
        }

        return from.isLowerThan(to) && inVerticalRange;
    }
}
