package chess.domain.piece.obstacleStrategy;

import chess.domain.position.Position;
import chess.domain.position.RelativePosition;

import java.util.ArrayList;
import java.util.List;

public class BlockedByObstacle implements ObstacleStrategy {

    @Override
    public List<Position> obstacleCheckingPositions(Position from, Position to) {
        RelativePosition relativePosition = RelativePosition.of(from, to);
        RelativePosition unitPosition = relativePosition.toUnit();
        Position currentPosition = from.move(unitPosition);
        List<Position> positions = new ArrayList<>();
        while (!currentPosition.equals(to)) {
            positions.add(currentPosition);
            currentPosition = currentPosition.move(unitPosition);
        }
        return positions;
    }
}
