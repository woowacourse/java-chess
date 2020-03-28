package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;

import java.util.List;

public class OrdinaryMovement implements MoveStrategy {

    private final List<Direction> directions;
    private final int moveCount;

    public OrdinaryMovement(List<Direction> directions, int moveCount) {
        this.directions = directions;
        this.moveCount = moveCount;
    }

    @Override
    public List<Position> findMovePath(Position source, Position target) {
        for (Direction direction : directions) {
            List<Position> path = source.pathTo(direction, moveCount);
            if (path.contains(target)) {
                return findPathTo(path, target);
            }
        }

        throw new InvalidMovementException();
    }

    private List<Position> findPathTo(List<Position> path, Position target) {
        return path.subList(0, path.indexOf(target));
    }

    @Override
    public List<Position> findKillPath(Position source, Position target) {
        return findMovePath(source, target);
    }
}
