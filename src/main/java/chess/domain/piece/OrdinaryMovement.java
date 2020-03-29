package chess.domain.piece;

import java.util.List;
import java.util.Map;

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
    protected void validatePath(Map<Position, GamePiece> board, Position source, Position target) {
        List<Position> path = findPath(source, target);

        validateObstacle(board, target, path);
    }

    private List<Position> findPath(Position source, Position target) {
        return directions.stream()
                .map(direction -> source.pathTo(direction, moveCount))
                .filter(eachPath -> eachPath.contains(target))
                .findFirst()
                .orElseThrow(() -> new InvalidMovementException("이동할 수 없는 경로입니다."));
    }

    private void validateObstacle(Map<Position, GamePiece> board, Position target, List<Position> path) {
        pathFromSourceToTarget(target, path).stream()
                .filter(position -> board.get(position) != EmptyPiece.getInstance())
                .findFirst()
                .ifPresent(position -> {
                    throw new InvalidMovementException("경로에 기물이 존재합니다.");
                });
    }

    private List<Position> pathFromSourceToTarget(Position target, List<Position> path) {
        return path.subList(0, path.indexOf(target));
    }
}
