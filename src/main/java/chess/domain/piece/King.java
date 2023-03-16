package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.List;
import java.util.Optional;

public class King extends Piece {

    public King(final File file, final Rank rank, final Color color) {
        super(file, rank, color);
    }

    @Override
    protected boolean canMove(final Position targetPosition) {
        final int manhattanDistance = position.calculateManhattanDistance(targetPosition);
        return manhattanDistance == 1 || (manhattanDistance == 2 && !position.isInCrossPosition(targetPosition));
    }

    @Override
    public Piece move(final Position targetPosition, final Optional<Piece> pieceContainerOfTargetPosition) {
        return null;
    }

    @Override
    public List<Position> getPassingPositions(final Position targetPosition) {
        validateSamePosition(targetPosition);
        validateDestination(targetPosition);
        return position.findPassingPositions(targetPosition);
    }
}
