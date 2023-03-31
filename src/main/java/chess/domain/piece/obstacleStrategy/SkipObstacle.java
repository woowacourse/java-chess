package chess.domain.piece.obstacleStrategy;

import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;

public final class SkipObstacle implements ObstacleStrategy {

    @Override
    public List<Position> getObstacleCheckingPositions(final Position from, final Position to) {
        return Collections.emptyList();
    }
}
