package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;

public abstract class GamePiece {

    protected final PlayerColor playerColor;
    private final String name;
    private final double score;

    public GamePiece(String name, double score, PlayerColor playerColor) {
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

    protected abstract void validatePath(Map<Position, GamePiece> board, Position source, Position target);

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
