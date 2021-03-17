package chess.domain.piece;

import chess.domain.player.PlayerType;

public class Queen extends Piece {
    private static final String NAME = "Q";

    public Queen(PlayerType playerType) {
        super(playerType, NAME);
    }
}
