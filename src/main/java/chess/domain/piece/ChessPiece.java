package chess.domain.piece;

import chess.domain.player.Player;

public abstract class ChessPiece {

    private final String name;
    private final Player player;

    public ChessPiece(String name, Player player) {
        this.player = player;
        this.name = player.decideName(name);
    }
}
