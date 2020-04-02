package chess.domain.piece;

import java.util.Collections;
import java.util.List;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;

public class OrdinaryMovement extends GamePiece {

    protected final List<Direction> directions;
    protected final int moveCount;

    public OrdinaryMovement(String name, List<Position> originalPositions, List<Direction> directions, int moveCount,
            double score, PlayerColor playerColor) {
        super(name, originalPositions, score, playerColor);
        this.directions = directions;
        this.moveCount = moveCount;
    }

    @Override
    protected boolean canMove(Board board, Position source, Position target) {
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
