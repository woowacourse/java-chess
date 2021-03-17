package chess.domain.piece;

import chess.domain.player.PlayerType;

public class Knight extends Piece {
    private static final String NAME = "N";

    public Knight(PlayerType playerType) {
        super(playerType, NAME);
    }
}
