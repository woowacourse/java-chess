package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;

import java.util.Collections;
import java.util.List;

public class OrdinaryMovement {

    private final List<Direction> directions;
    private final int moveCount;

    public OrdinaryMovement(List<Direction> directions, int moveCount) {
        this.directions = directions;
        this.moveCount = moveCount;
    }

    public boolean canMove(Board board, Position source, Position target) {
        List<Position> path = findPath(source, target);

        if (path.equals(Collections.emptyList())) {
            return false;
        }
        return canMoveByObstacle(board, target, path);
    }

    private List<Position> findPath(Position source, Position target) {
        return directions.stream()
                .map(direction -> source.pathTo(direction, moveCount))
                .filter(eachPath -> eachPath.contains(target))
                .findFirst()
                .orElse(Collections.emptyList());
    }

    private boolean canMoveByObstacle(Board board, Position target, List<Position> path) {
        return pathFromSourceToTarget(target, path).stream()
                .noneMatch(board::isNotEmpty);
    }

    private List<Position> pathFromSourceToTarget(Position target, List<Position> path) {
        return path.subList(0, path.indexOf(target));
    }
}
