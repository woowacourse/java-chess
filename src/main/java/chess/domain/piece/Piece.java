package chess.domain.piece;

import chess.domain.player.PlayerType;

public abstract class Piece {

    private final PlayerType playerType;
    private final String name;

    public Piece(PlayerType playerType, String name) {
        this.playerType = playerType;
        this.name = name;
    }

    public String getName() {
        if (playerType == PlayerType.WHITE) {
            return name.toLowerCase();
        }
        return name;
    }
}
