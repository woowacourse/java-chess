package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;

public class Pawn extends GamePiece {

    private static final int FIRST_MOVE_COUNT = 2;
    private Direction moveDirection;
    private List<Direction> killDirections;
    private int moveCount;

    public Pawn(PlayerColor playerColor) {
        super("p", Arrays.stream(Column.values()).map(file -> Position.of(file, Row.TWO)).collect(Collectors.toList()),
                1, playerColor);
        moveCount = 1;
        moveDirection = Direction.N;
        killDirections = Arrays.asList(Direction.NW, Direction.NE);
        if (playerColor.equals(PlayerColor.BLACK)) {
            moveDirection = Direction.S;
            killDirections = Arrays.asList(Direction.SE, Direction.SW);
        }
    }

    @Override
    protected void validatePath(Map<Position, GamePiece> board, Position source, Position target) {
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
        if (originalPositions.contains(source)) {
            path = source.pathTo(moveDirection, FIRST_MOVE_COUNT);
        }

        if (!path.contains(target)) {
            throw new InvalidMovementException("이동할 수 없는 경로입니다.");
        }

        path.stream()
                .filter(position -> board.get(position) != EmptyPiece.getInstance())
                .findFirst()
                .ifPresent(position -> {
                    throw new InvalidMovementException("경로에 기물이 존재합니다.");
                });
    }
}
