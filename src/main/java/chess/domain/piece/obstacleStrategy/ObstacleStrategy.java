package chess.domain.piece.obstacleStrategy;

import chess.domain.position.Position;

import java.util.List;

public interface ObstacleStrategy {
    List<Position> obstacleCheckingPositions(Position from, Position to);
}
