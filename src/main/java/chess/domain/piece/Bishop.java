package chess.domain.piece;

import chess.domain.player.Player;

public class Bishop extends ChessPiece {

    private static final String NAME = "B";

    public Bishop(Player player) {
        super(NAME, player);
    }
}
