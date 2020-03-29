package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.player.PlayerColor;

import java.util.*;
import java.util.stream.Collectors;

public class GamePiece {

    public static final GamePiece EMPTY = new GamePiece(null, null, ".");
    private static final Map<String, GamePiece> GAME_PIECES;

    static {
        GAME_PIECES = new HashMap<>();
        for (ChessPiece piece : ChessPiece.values()) {
            for (PlayerColor playerColor : PlayerColor.values()) {
                GAME_PIECES.put(key(piece, playerColor), new GamePiece(piece, playerColor, playerColor.decideName(piece.getName())));
            }
        }
    }

    private final ChessPiece chessPiece;
    private final PlayerColor playerColor;
    private final String name;

    private GamePiece(ChessPiece chessPiece, PlayerColor playerColor, String name) {
        this.chessPiece = chessPiece;
        this.playerColor = playerColor;
        this.name = name;
    }

    public static GamePiece of(ChessPiece piece, PlayerColor playerColor) {
        return GAME_PIECES.get(key(piece, playerColor));
    }

    private static String key(ChessPiece piece, PlayerColor playerColor) {
        return piece.toString() + playerColor.toString();
    }

    public static List<GamePiece> list() {
        return new ArrayList<>(GAME_PIECES.values());
    }

    public void validateMoveTo(Map<Position, GamePiece> board, Position source, Position target) {
        GamePiece targetPiece = board.get(target);
    }

    public List<Position> searchMovePath(Position source, Position target) {
        return chessPiece.validateMoveTo(source, target);
    }

    public List<Position> searchKillPath(Position source, Position target) {
        return chessPiece.searchKillPath(source, target);
    }

    public boolean isEnemy(GamePiece sourcePiece) {
        return playerColor != sourcePiece.playerColor;
    }

    public boolean is(PlayerColor playerColor) {
        return playerColor.equals(this.playerColor);
    }

    public boolean isPawn() {
        return ChessPiece.PAWN.equals(chessPiece);
    }

    public boolean isKing() {
        return ChessPiece.KING.equals(chessPiece);
    }

    public double calculateScore(int count) {
        return chessPiece.calculateScore(count);
    }

    public List<Position> getInitialPositions() {
        List<Position> positions = chessPiece.getOriginalPositions()
                .stream()
                .map(playerColor::reviseInitialPosition)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(positions);
    }

    public String getName() {
        return name;
    }
}
