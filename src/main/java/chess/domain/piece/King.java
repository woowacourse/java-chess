package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.position.Position;

import java.util.List;

import static chess.domain.position.Position.CROSS_ADJACENT_MANHATTAN_DISTANCE;
import static chess.domain.position.Position.DIAGONAL_ADJACENT_MANHATTAN_DISTANCE;

public class King extends Piece {

    public King(final Position position, final Color color) {
        super(position, color);
    }

    @Override
    protected boolean canMove(final Position targetPosition) {
        final int manhattanDistance = position.calculateManhattanDistance(targetPosition);
        return manhattanDistance == CROSS_ADJACENT_MANHATTAN_DISTANCE ||
                (manhattanDistance == DIAGONAL_ADJACENT_MANHATTAN_DISTANCE && !position.isInCrossPosition(targetPosition));
    }

    @Override
    public Piece move(final Piece pieceInTargetPosition) {
        final Position targetPosition = pieceInTargetPosition.getPosition();
        validateDestination(targetPosition);
        validateCatchingSameColor(pieceInTargetPosition);
        return new King(targetPosition, color);
    }

    @Override
    public List<Position> getPassingPositions(final Position targetPosition) {
        validateSamePosition(targetPosition);
        validateDestination(targetPosition);
        return position.findPassingPositions(targetPosition);
    }
}
