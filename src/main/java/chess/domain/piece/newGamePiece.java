package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class newGamePiece {

    protected final List<Position> originalPositions;
    protected final List<Direction> directions;
    protected final int moveCount;
    private final String name;
    private final PlayerColor playerColor;
    private final double score;

    public newGamePiece(String name, List<Position> originalPositions, List<Direction> directions, int moveCount, double score, PlayerColor playerColor) {
        this.name = name;
        this.originalPositions = Collections.unmodifiableList(originalPositions);
        this.directions = directions;
        this.moveCount = moveCount;
        this.score = score;
        this.playerColor = playerColor;
    }

    public void validateMoveTo(Map<Position, newGamePiece> board, Position source, Position target) {
        newGamePiece targetPiece = board.get(target);

        if (playerColor == targetPiece.playerColor) {
            throw new InvalidMovementException("자신의 말은 잡을 수 없습니다.");
        }

        validatePath(board, source, target);
    }

    public abstract void validatePath(Map<Position, newGamePiece> board, Position source, Position target);

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
