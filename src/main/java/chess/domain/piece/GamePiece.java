package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;

public abstract class GamePiece {

    private final String name;
    private final double score;
    protected final PlayerColor playerColor;
    protected final List<Direction> directions;
    protected final int moveCount;

    protected GamePiece(String name, double score, PlayerColor playerColor,
            List<Direction> directions, int moveCount) {
        this.directions = directions;
        this.moveCount = moveCount;
        this.name = playerColor.decideName(name);
        this.score = score;
        this.playerColor = playerColor;
    }

    public List<String> searchPaths(Board board, Position source) {
        List<Position> paths = new ArrayList<>();

        for (Direction direction : directions) {
            List<Position> path = source.pathTo(direction, moveCount);
            paths.addAll(findMovablePositions(board, path));
        }

        return paths.stream()
                .map(Position::getName)
                .collect(Collectors.toList());
    }

    private List<Position> findMovablePositions(Board board, List<Position> path) {
        List<Position> possiblePath = new ArrayList<>();
        for (int i = 0; i < path.size() && !board.isNotEmpty(path.get(i)); i++) {
            Position position = path.get(i);
            possiblePath.add(position);
        }

        checkKillPosition(board, path, possiblePath);

        return possiblePath;
    }

    private void checkKillPosition(Board board, List<Position> path, List<Position> possiblePath) {
        try {
            Position position = path.get(possiblePath.size());
            if (board.isNotEmpty(position) && !board.isSameColor(this, position)) {
                possiblePath.add(position);
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    public void validateMoveTo(Board board, Position source, Position target) {
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

    private void validateObstacle(Board board, Position target, List<Position> path) {
        pathFromSourceToTarget(target, path).stream()
                .filter(board::isNotEmpty)
                .findFirst()
                .ifPresent(position -> {
                    throw new InvalidMovementException("경로에 기물이 존재합니다.");
                });
    }

    protected List<Position> pathFromSourceToTarget(Position target, List<Position> path) {
        return path.subList(0, path.indexOf(target));
    }

    public boolean is(PlayerColor playerColor) {
        return playerColor.equals(this.playerColor);
    }

    public double calculateScore(int count) {
        return score * count;
    }

    public boolean isKing() {
        return this instanceof King;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }

    public boolean isSameColor(GamePiece gamePiece) {
        return playerColor.equals(gamePiece.playerColor);
    }

    public abstract List<Position> getOriginalPositions();

    public String getName() {
        return name;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof GamePiece))
            return false;
        GamePiece gamePiece = (GamePiece)o;
        return Objects.equals(name, gamePiece.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
