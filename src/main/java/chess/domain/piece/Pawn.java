package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;

public class Pawn extends GamePiece {

    private static final String NAME = "p";
    private static final int SCORE = 1;
    private static final int MOVE_COUNT = 1;
    private static List<Position> originalPositions = Arrays.stream(Column.values()).
            map(file -> Position.of(file, Row.TWO))
            .collect(Collectors.toList());

    private static final int FIRST_MOVE_COUNT = 2;
    private Direction moveDirection;
    private List<Direction> killDirections;

    public Pawn(PlayerColor playerColor) {
        super(NAME, SCORE, playerColor, Collections.EMPTY_LIST, MOVE_COUNT);
        moveDirection = Direction.N;
        killDirections = Arrays.asList(Direction.NW, Direction.NE);
        if (playerColor.equals(PlayerColor.BLACK)) {
            moveDirection = Direction.S;
            killDirections = Arrays.asList(Direction.SE, Direction.SW);
        }
    }

    @Override
    public List<String> searchPaths(Board board, Position source) {
        List<Position> paths = new ArrayList<>();
        List<Position> path = decideMovePath(source);

        paths.addAll(findMovablePositions(board, path));
        paths.addAll(findKillPath(board, source));

        return paths.stream()
                .map(Position::getName)
                .collect(Collectors.toList());
    }

    private List<Position> decideMovePath(Position source) {
        List<Position> path = source.pathTo(moveDirection, moveCount);
        if (playerColor.reviseInitialPositions(originalPositions).contains(source)) {
            path = source.pathTo(moveDirection, FIRST_MOVE_COUNT);
        }
        return path;
    }

    private List<Position> findMovablePositions(Board board, List<Position> path) {
        List<Position> possiblePath = new ArrayList<>();
        for (int i = 0; i < path.size() && !board.isNotEmpty(path.get(i)); i++) {
            Position position = path.get(i);
            possiblePath.add(position);
        }
        return possiblePath;
    }

    private List<Position> findKillPath(Board board, Position source) {
        List<Position> possiblePaths = new ArrayList<>();
        for (Direction killDirection : killDirections) {
            Position position = source.nextPositionOf(killDirection).orElse(null);
            checkKillPosition(board, position, possiblePaths);
        }
        return possiblePaths;
    }

    private void checkKillPosition(Board board, Position position, List<Position> paths) {
        if (position != null && board.isNotEmpty(position) && !board.isSameColor(this, position)) {
            paths.add(position);
        }
    }

    @Override
    public void validateMoveTo(Board board, Position source, Position target) {
        if (board.isNotEmpty(target)) {
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

    private void validateMovePath(Board board, Position source, Position target) {
        List<Position> path = decideMovePath(source);

        if (!path.contains(target)) {
            throw new InvalidMovementException("이동할 수 없는 경로입니다.");
        }

        pathFromSourceToTarget(target, path).stream()
                .filter(board::isNotEmpty)
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
