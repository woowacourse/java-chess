package chess.domain.piece;

import chess.domain.player.Player;

public class Knight extends ChessPiece {

    private static final String NAME = "N";

    public Knight(Player player) {
        super(NAME, player);
    }
}
