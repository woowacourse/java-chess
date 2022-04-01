package chess.domain.piece.movingstrategy;

import chess.domain.piece.PieceColor;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.YAxis;

public class PawnMovingStrategy extends MovingStrategy {

    private static final int INITIAL_STEP_LIMIT = 2;
    private static final int NORMAL_STEP_LIMIT = 1;

    @Override
    boolean isPossibleStep(Position from, Position to, PieceColor pieceColor) {
        if (isInitialPosition(from, pieceColor)) {
            return from.isVerticalDistanceShorterThan(to, INITIAL_STEP_LIMIT);
        }

        return from.isVerticalDistanceShorterThan(to, NORMAL_STEP_LIMIT);
    }

    private boolean isInitialPosition(Position from, PieceColor pieceColor) {
        if (pieceColor.equals(PieceColor.WHITE)) {
            return from.isSameYAxis(YAxis.TWO);
        }

        return from.isSameYAxis(YAxis.SEVEN);
    }

    @Override
    boolean isPossibleDirection(Position from, Position to, PieceColor pieceColor) {
        Direction direction = Direction.of(from, to);

        if (pieceColor.equals(PieceColor.BLACK)) {
            return direction.equals(Direction.VERTICAL_DOWN);
        }

        return direction.equals(Direction.VERTICAL_UP);
    }

    @Override
    public boolean isAbleToAttack(Position from, Position to, PieceColor pieceColor) {
        Direction direction = Direction.of(from, to);
        if (pieceColor.equals(PieceColor.BLACK)) {
            return direction.equals(Direction.DIAGONAL_LEFT_DOWN) || direction.equals(Direction.DIAGONAL_RIGHT_DOWN);
        }

        return direction.equals(Direction.DIAGONAL_LEFT_UP) || direction.equals(Direction.DIAGONAL_RIGHT_UP);
    }
}
