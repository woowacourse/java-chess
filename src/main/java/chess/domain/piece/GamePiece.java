package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class GamePiece {

    protected final List<Position> originalPositions;
    protected final List<Direction> directions;
    protected final int moveCount;
    protected final PlayerColor playerColor;
    private final String name;
    private final double score;

    public GamePiece(String name, List<Position> originalPositions, List<Direction> directions, int moveCount, double score, PlayerColor playerColor) {
        this.name = playerColor.decideName(name);
        this.originalPositions = playerColor.reviseInitialPositions(originalPositions);
        this.directions = directions;
        this.moveCount = moveCount;
        this.score = score;
        this.playerColor = playerColor;
    }

    public void validateMoveTo(Map<Position, GamePiece> board, Position source, Position target) {
        GamePiece targetPiece = board.get(target);

        if (playerColor == targetPiece.playerColor) {
            throw new InvalidMovementException("자신의 말은 잡을 수 없습니다.");
        }

        validatePath(board, source, target);
    }

    private List<Position> backWard(List<Position> path) {
        return path.stream()
                .map(Position::opposite)
                .collect(Collectors.toList());
    }

    public abstract void validatePath(Map<Position, GamePiece> board, Position source, Position target);

    public boolean is(PlayerColor playerColor) {
        return playerColor.equals(this.playerColor);
    }

    public double calculateScore(int count) {
        return score * count;
    }

    public List<Position> getOriginalPositions() {
        return originalPositions;
    }

    public String getName() {
        return name;
    }
}
