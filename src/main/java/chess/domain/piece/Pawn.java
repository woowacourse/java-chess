package chess.domain.piece;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;

public class Pawn extends GamePiece {

    private static List<Position> originalPositions = Arrays.stream(Column.values()).
            map(file -> Position.of(file, Row.TWO))
            .collect(Collectors.toList());

    private static final int FIRST_MOVE_COUNT = 2;
    private Direction moveDirection;
    private List<Direction> killDirections;

    public Pawn(PlayerColor playerColor) {
        super("p", 1, playerColor, Collections.EMPTY_LIST, 1);
        moveDirection = Direction.N;
        killDirections = Arrays.asList(Direction.NW, Direction.NE);
        if (playerColor.equals(PlayerColor.BLACK)) {
            moveDirection = Direction.S;
            killDirections = Arrays.asList(Direction.SE, Direction.SW);
        }
    }

    @Override
    public void validateMoveTo(Map<Position, GamePiece> board, Position source, Position target) {
        GamePiece targetPiece = board.get(target);

        if (playerColor == targetPiece.playerColor) {
            throw new InvalidMovementException("자신의 말은 잡을 수 없습니다.");
        }

        validatePath(board, source, target);
    }

    private void validatePath(Map<Position, GamePiece> board, Position source, Position target) {
        if (board.get(target) != EmptyPiece.getInstance()) {
            validateKillPath(source, target);
            return;
        }

        validateMovePath(board, source, target);
    }

    private void validateKillPath(Position source, Position target) {
        killDirections.stream()
                .map(direction -> source.pathTo(direction, moveCount))
                .filter(eachPath -> eachPath.contains(target))
                .findFirst()
                .orElseThrow(() -> new InvalidMovementException("이동할 수 없는 경로입니다."));
    }

    private void validateMovePath(Map<Position, GamePiece> board, Position source, Position target) {
        List<Position> path = source.pathTo(moveDirection, moveCount);
        if (playerColor.reviseInitialPositions(originalPositions).contains(source)) {
            path = source.pathTo(moveDirection, FIRST_MOVE_COUNT);

        }

        if (!path.contains(target)) {
            throw new InvalidMovementException("이동할 수 없는 경로입니다.");
        }

        pathFromSourceToTarget(target, path).stream()
                .filter(position -> board.get(position) != EmptyPiece.getInstance())
                .findFirst()
                .ifPresent(position -> {
                    throw new InvalidMovementException("경로에 기물이 존재합니다.");
                });
    }

    @Override
    public List<Position> getOriginalPositions() {
        return playerColor.reviseInitialPositions(originalPositions);
    }
}
