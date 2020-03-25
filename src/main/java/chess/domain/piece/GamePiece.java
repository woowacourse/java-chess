package chess.domain.piece;

import chess.domain.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GamePiece {

    public static final GamePiece empty = new GamePiece(null, null, ".");

    private final ChessPiece chessPiece;
    private final Player player;
    private final String name;

    private GamePiece(ChessPiece chessPiece, Player player, String name) {
        this.chessPiece = chessPiece;
        this.player = player;
        this.name = name;
    }

    public static GamePiece of(ChessPiece piece, Player player) {
        return new GamePiece(piece, player, player.decideName(piece.getName()));
    }

    public static List<GamePiece> list() {
        List<GamePiece> gamePieces = new ArrayList<>();
        for (ChessPiece piece : ChessPiece.values()) {
            for (Player player : Player.values()) {
                gamePieces.add(GamePiece.of(piece, player));
            }
        }

        return gamePieces;
    }

    public String getName() {
        return name;
    }
}
