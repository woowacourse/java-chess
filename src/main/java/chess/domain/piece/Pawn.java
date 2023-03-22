package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    private static final Map<Color, Rank> INITIAL_RANK = Map.of(
            Color.BLACK, Rank.SEVEN,
            Color.WHITE, Rank.TWO
    );

    public Pawn(final File file, final Rank rank, final Color color) {
        super(file, rank, color);
    }

    @Override
    protected boolean canMove(final Position targetPosition) {
        if (isInitialPosition()) {
            return position.calculateManhattanDistance(targetPosition) <= 2 && isOppositeColorSide(targetPosition);
        }
        return isNeighborDistance(targetPosition) && isOppositeColorSide(targetPosition);
    }

    @Override
    public Piece move(final Piece pieceInTargetPosition) {
        final Position targetPosition = pieceInTargetPosition.getPosition();
        validateDestination(targetPosition);
        validateDiagonalColor(pieceInTargetPosition, targetPosition);
        validateVerticalColor(pieceInTargetPosition, targetPosition);

        return new Pawn(targetPosition.getFile(), targetPosition.getRank(), color);
    }

    private void validateDiagonalColor(final Piece pieceInTargetPosition, final Position targetPosition) {
        if (position.isInDiagonalPosition(targetPosition)
                && !pieceInTargetPosition.isSameColor(color.getOppositeColor())) {
            throw new IllegalArgumentException(INVALID_DESTINATION_MESSAGE);
        }
    }

    private void validateVerticalColor(final Piece pieceInTargetPosition, final Position targetPosition) {
        if (position.isInCrossPosition(targetPosition) && !pieceInTargetPosition.isSameColor(Color.BLANK)) {
            throw new IllegalArgumentException(INVALID_DESTINATION_MESSAGE);
        }
    }

    private boolean isNeighborDistance(final Position targetPosition) {
        final int manhattanDistance = position.calculateManhattanDistance(targetPosition);
        return manhattanDistance == 1 || (manhattanDistance == 2 && !position.isInCrossPosition(targetPosition));
    }

    private boolean isOppositeColorSide(final Position targetPosition) {
        if (color == Color.BLACK) {
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
