package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;

public abstract class GamePiece {

    private final String name;
    private final double score;
    protected final PlayerColor playerColor;
    protected final List<Direction> directions;
    protected final int moveCount;

    public GamePiece(String name, double score, PlayerColor playerColor,
            List<Direction> directions, int moveCount) {
        this.directions = directions;
        this.moveCount = moveCount;
        this.name = playerColor.decideName(name);
        this.score = score;
        this.playerColor = playerColor;
    }

    public static List<GamePiece> createGamePieces() {
        List<GamePiece> gamePieces = new ArrayList<>();
        gamePieces.add(new King(PlayerColor.BLACK));
        gamePieces.add(new Queen(PlayerColor.BLACK));
        gamePieces.add(new Bishop(PlayerColor.BLACK));
        gamePieces.add(new Knight(PlayerColor.BLACK));
        gamePieces.add(new Rook(PlayerColor.BLACK));
        gamePieces.add(new Pawn(PlayerColor.BLACK));
        gamePieces.add(new King(PlayerColor.WHITE));
        gamePieces.add(new Queen(PlayerColor.WHITE));
        gamePieces.add(new Bishop(PlayerColor.WHITE));
        gamePieces.add(new Knight(PlayerColor.WHITE));
        gamePieces.add(new Rook(PlayerColor.WHITE));
        gamePieces.add(new Pawn(PlayerColor.WHITE));

        return gamePieces;
    }

    public void validateMoveTo(Map<Position, GamePiece> board, Position source, Position target) {
        GamePiece targetPiece = board.get(target);

        if (playerColor == targetPiece.playerColor) {
            throw new InvalidMovementException("자신의 말은 잡을 수 없습니다.");
        }

        validatePath(board, source, target);
    }

    private void validatePath(Map<Position, GamePiece> board, Position source, Position target) {
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

    public abstract List<Position> getOriginalPositions();

    public String getName() {
        return name;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }
}
