package chess.domain.piece;

import chess.domain.player.PlayerType;

public class Bishop extends Piece {
    private static final String NAME = "B";

    public Bishop(PlayerType playerType) {
        super(playerType, NAME);
    }
}
