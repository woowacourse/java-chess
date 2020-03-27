package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.player.Player;

import java.util.*;
import java.util.stream.Collectors;

public class GamePiece {

    public static final GamePiece EMPTY = new GamePiece(null, null, ".");
    private static final Map<String, GamePiece> GAME_PIECES;

    static {
        GAME_PIECES = new HashMap<>();
        for (ChessPiece piece : ChessPiece.values()) {
            for (Player player : Player.values()) {
                GAME_PIECES.put(key(piece, player), new GamePiece(piece, player, player.decideName(piece.getName())));
            }
        }
    }

    private final ChessPiece chessPiece;
    private final Player player;
    private final String name;

    private GamePiece(ChessPiece chessPiece, Player player, String name) {
        this.chessPiece = chessPiece;
        this.player = player;
        this.name = name;
    }

    public static GamePiece of(ChessPiece piece, Player player) {
        return GAME_PIECES.get(key(piece, player));
    }

    private static String key(ChessPiece piece, Player player) {
        return piece.toString() + player.toString();
    }

    public static List<GamePiece> list() {
        return new ArrayList<>(GAME_PIECES.values());
    }

    public List<Position> searchPath(Position source, Position target, boolean isKill) {
        return chessPiece.searchPath(source, target, isKill);
    }

    public boolean isEnemy(GamePiece sourcePiece) {
        return player != sourcePiece.player;
    }

    public boolean isWhite() {
        return player.equals(Player.WHITE);
    }

    public boolean isPawn() {
        return chessPiece.equals(ChessPiece.PAWN);
    }

    public boolean isKing() {
        return this != EMPTY && chessPiece.equals(ChessPiece.KING);
    }

    public double calculateScore(int count) {
        return chessPiece.calculateScore(count);
    }

    public List<Position> getInitialPositions() {
        List<Position> positions = chessPiece.getOriginalPositions()
                .stream()
                .map(player::reviseInitialPosition)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(positions);
    }

    public String getName() {
        return name;
    }
}
