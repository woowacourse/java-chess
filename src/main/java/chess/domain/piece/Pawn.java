package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.YAxis;
import chess.domain.position.direction.DiagonalDirection;
import chess.domain.position.direction.Direction;
import chess.domain.position.direction.VerticalDirection;
import java.util.List;

public class Pawn extends AbstractPiece {

    private static final int INITIAL_MOVE_RANGE = 2;

    private final List<Direction> directions;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor, PieceType.PAWN);
        this.directions = List.of(new VerticalDirection());
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return usePawnStrategy(from, to);
    }

    private boolean usePawnStrategy(Position from, Position to) {
        if (isInitialPosition(from, getPieceColor())) {
            return getMovableIfInitialPosition(from, to);
        }

        return getMovableIfNotInitialPosition(from, to);
    }

    @Override
    public boolean isAbleToAttack(Position from, Position to) {
        Direction diagonalDirection = new DiagonalDirection();
        VerticalDirection verticalDirection = new VerticalDirection();
        boolean isDiagonalOneDistance =
                diagonalDirection.isOnDirection(from, to) && verticalDirection.isInVerticalRange(from, to, 1);

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
        return directions.stream()
                .anyMatch(direction -> {
                    if (isPieceColor(PieceColor.BLACK)) {
                        return direction.isOnDirection(from, to) && from.isFarOneOnXAxis(to) && from.isUpperThan(to);
                    }
                    return direction.isOnDirection(from, to) && from.isFarOneOnXAxis(to) && from.isLowerThan(to);
                });
    }
}
