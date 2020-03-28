package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;

import java.util.Arrays;
import java.util.List;

public class PawnStrategy implements MoveStrategy {

    private final Direction direction = Direction.N;
    private final List<Direction> killDirections = Arrays.asList(Direction.NE, Direction.NW);

    @Override
    public List<Position> findMovePath(Position source, Position target) {
        List<Position> path = source.pathTo(direction, 1);
        if (path.contains(target)) {
            return findPathTo(path, target);
        }

        path = source.pathTo(direction, 2);
        if (path.contains(target)) {
            return findPathTo(path, target);
        }

        throw new InvalidMovementException();
    }

    @Override
    public List<Position> findKillPath(Position source, Position target) {
        for (Direction direction : killDirections) {
            List<Position> path = source.pathTo(direction, 1);
            if (path.contains(target)) {
                return findPathTo(path, target);
            }
        }

        throw new InvalidMovementException();
    }

    private List<Position> findPathTo(List<Position> path, Position target) {
        return path.subList(0, path.indexOf(target));
    }
}