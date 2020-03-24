package chess.domain.piece;

import chess.domain.player.Player;

public class King extends ChessPiece {

    private static final String NAME = "K";

    public King(Player player) {
        super(NAME, player);
    }
}
