package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.List;

import static chess.domain.Position.CROSS_ADJACENT_MANHATTAN_DISTANCE;
import static chess.domain.Position.DIAGONAL_ADJACENT_MANHATTAN_DISTANCE;

public class King extends Piece {

    public King(final File file, final Rank rank, final Color color) {
        super(file, rank, color);
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
        return new King(targetPosition.getFile(), targetPosition.getRank(), color);
    }

    @Override
    public List<Position> getPassingPositions(final Position targetPosition) {
        validateSamePosition(targetPosition);
        validateDestination(targetPosition);
        return position.findPassingPositions(targetPosition);
    }
}
