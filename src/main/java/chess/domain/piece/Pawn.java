package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public Piece move(final Position targetPosition, final Optional<Piece> pieceContainerOfTargetPosition) {
        return null;
    }

    private boolean isNeighborDistance(final Position targetPosition) {
        final int manhattanDistance = position.calculateManhattanDistance(targetPosition);
        return manhattanDistance == 1 || (manhattanDistance == 2 && !position.isInCrossPosition(targetPosition));
    }

    private boolean isOppositeColorSide(final Position targetPosition) {
        if (color.isBlack()) {
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
