package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.position.Path;
import chess.domain.position.Position;

public class Knight extends Piece {

    private static final int MOVABLE_MANHATTAN_DISTANCE = 3;

    public Knight(final Position position, final Color color) {
        super(position, color);
    }

    @Override
    protected boolean canMove(final Position targetPosition) {
        return !position.isInCrossPosition(targetPosition)
                && position.calculateManhattanDistance(targetPosition) == MOVABLE_MANHATTAN_DISTANCE;
    }

    @Override
    public Piece move(final Piece pieceInTargetPosition) {
        final Position targetPosition = pieceInTargetPosition.getPosition();
        validateDestination(targetPosition);
        validateCatchingSameColor(pieceInTargetPosition);
        return new Knight(targetPosition, color);
    }

    @Override
    public Path getPassingPositions(final Position targetPosition) {
        validateSamePosition(targetPosition);
        validateDestination(targetPosition);
        return Path.emptyPath();
    }
}
