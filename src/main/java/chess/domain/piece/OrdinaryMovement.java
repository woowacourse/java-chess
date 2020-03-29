package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;

import java.util.List;
import java.util.Map;

public class OrdinaryMovement extends GamePiece {

    public OrdinaryMovement(String name, List<Position> originalPositions, List<Direction> directions, int moveCount, double score, PlayerColor playerColor) {
        super(name, originalPositions, directions, moveCount, score, playerColor);
    }

    @Override
    public void validatePath(Map<Position, GamePiece> board, Position source, Position target) {
        // target을 포함하는 path를 구함, 없다면 에러 throw
        List<Position> path = directions.stream()
                .map(direction -> source.pathTo(direction, moveCount))
                .filter(eachPath -> eachPath.contains(target))
                .findFirst()
                .orElseThrow(() -> new InvalidMovementException("이동할 수 없는 경로입니다."));

        // Path에 장애물이 있나 확인
        for (Position position : pathFromSourceToTarget(target, path)) {
            if (board.get(position) != EmptyPiece.getInstance()) {
                throw new InvalidMovementException("장애물이 있습니다.");
            }
        }
    }

    private List<Position> pathFromSourceToTarget(Position target, List<Position> path) {
        return path.subList(0, path.indexOf(target));
    }
}
