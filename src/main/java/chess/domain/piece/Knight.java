package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.List;

public class Knight extends Piece {

    private static final int MOVABLE_MANHATTAN_DISTANCE = 3;
    private static final List<Position> PASSING_POSITIONS_WITH_LEAPING = List.of();

    public Knight(final File file, final Rank rank, final Color color) {
        super(file, rank, color);
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
        return new Knight(targetPosition.getFile(), targetPosition.getRank(), color);
    }

    @Override
    public List<Position> getPassingPositions(final Position targetPosition) {
        validateSamePosition(targetPosition);
        validateDestination(targetPosition);
        return PASSING_POSITIONS_WITH_LEAPING;
    }
}
