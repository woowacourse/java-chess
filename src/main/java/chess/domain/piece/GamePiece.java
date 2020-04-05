package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<String> paths = new ArrayList<>();

        for (Direction direction : directions) {
            List<Position> positions = source.pathTo(direction, moveCount);
            finaMovablePositions(board, paths, positions);
        }

        return paths;
    }

    private void finaMovablePositions(Board board, List<String> paths, List<Position> positions) {
        for (Position position : positions) {
            if (board.isNotEmpty(position) && board.isSameColor(this, position)) {
                break;
            }
            if (board.isNotEmpty(position) && !board.isSameColor(this, position)) {
                paths.add(position.getName());
                break;
            }
            paths.add(position.getName());
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
