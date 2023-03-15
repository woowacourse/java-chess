package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.Collections;
import java.util.List;

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
    public List<Position> getPassingPath(final Position targetPosition) {
        validateSamePosition(targetPosition);
        if (!canMove(targetPosition)) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
        return Collections.emptyList();
    }
}
