package chess.domain.piece;

import java.util.List;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;

public abstract class GamePiece {

    protected final List<Position> originalPositions;
    protected final PlayerColor playerColor;
    private final String name;
    private final double score;

    public GamePiece(String name, List<Position> originalPositions, double score, PlayerColor playerColor) {
        this.name = playerColor.decideName(name);
        this.originalPositions = playerColor.reviseInitialPositions(originalPositions);
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

    protected abstract void validatePath(Map<Position, GamePiece> board, Position source, Position target);

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
