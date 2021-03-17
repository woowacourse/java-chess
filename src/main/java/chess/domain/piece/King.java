package chess.domain.piece;

import chess.domain.player.PlayerType;

public class King extends Piece {
    private static final String NAME = "K";

    public King(PlayerType playerType) {
        super(playerType, NAME);
    }
}
