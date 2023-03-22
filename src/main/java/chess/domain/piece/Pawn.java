package chess.domain.piece;

import chess.constant.ExceptionCode;
import chess.domain.piece.property.Color;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.List;
import java.util.Map;

import static chess.domain.position.Position.CROSS_ADJACENT_MANHATTAN_DISTANCE;
import static chess.domain.position.Position.DIAGONAL_ADJACENT_MANHATTAN_DISTANCE;

public class Pawn extends Piece {

    private static final Map<Color, Rank> INITIAL_RANK = Map.of(
            Color.BLACK, Rank.SEVEN,
            Color.WHITE, Rank.TWO
    );

    public Pawn(final Position position, final Color color) {
        super(position, color);
    }

    @Override
    protected boolean canMove(final Position targetPosition) {
        if (isInitialPosition()) {
            return position.calculateManhattanDistance(targetPosition) <= DIAGONAL_ADJACENT_MANHATTAN_DISTANCE
                    && isOppositeColorSide(targetPosition);
        }
        return isNeighborDistance(targetPosition) && isOppositeColorSide(targetPosition);
    }

    @Override
    public Piece move(final Piece pieceInTargetPosition) {
        final Position targetPosition = pieceInTargetPosition.getPosition();
        validateDestination(targetPosition);
        validateDiagonalColor(pieceInTargetPosition, targetPosition);
        validateHorizontalColor(pieceInTargetPosition, targetPosition);

        return new Pawn(targetPosition, color);
    }

    private void validateDiagonalColor(final Piece pieceInTargetPosition, final Position targetPosition) {
        if (position.isInDiagonalPosition(targetPosition)
                && !pieceInTargetPosition.isSameColor(color.getOppositeColor())) {
            throw new IllegalArgumentException(ExceptionCode.INVALID_DESTINATION.name());
        }
    }

    private void validateHorizontalColor(final Piece pieceInTargetPosition, final Position targetPosition) {
        if (position.isInCrossPosition(targetPosition) && !pieceInTargetPosition.isSameColor(Color.BLANK)) {
            throw new IllegalArgumentException(ExceptionCode.INVALID_DESTINATION.name());
        }
    }

    private boolean isNeighborDistance(final Position targetPosition) {
        final int manhattanDistance = position.calculateManhattanDistance(targetPosition);
        return manhattanDistance == CROSS_ADJACENT_MANHATTAN_DISTANCE
                || (manhattanDistance == DIAGONAL_ADJACENT_MANHATTAN_DISTANCE && !position.isInCrossPosition(targetPosition));
    }

    private boolean isOppositeColorSide(final Position targetPosition) {
        if (color.equals(Color.BLACK)) {
            return position.isUpperRankThan(targetPosition);
        }
        return position.isLowerRankThan(targetPosition);
    }

    private boolean isInitialPosition() {
        return position.getRank() == INITIAL_RANK.get(color);
    }

    @Override
    public List<Position> getPassingPositions(final Position targetPosition) {
        validateSamePosition(targetPosition);
        validateDestination(targetPosition);
        return position.findPassingPositions(targetPosition);
    }
}
