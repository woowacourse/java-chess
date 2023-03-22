package chess.domain.piece.obstacleStrategy;

import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;

public class SkipObstacle implements ObstacleStrategy {

    @Override
    public List<Position> obstacleCheckingPositions(Position from, Position to) {
        return Collections.emptyList();
    }
}
