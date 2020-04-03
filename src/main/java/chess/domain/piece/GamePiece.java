package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.player.PlayerColor;

import java.util.List;

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

    public abstract boolean canMoveTo(Board board, Position source, Position target);

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

    public PlayerColor getPlayerColor() {
        return playerColor;
    }
}
