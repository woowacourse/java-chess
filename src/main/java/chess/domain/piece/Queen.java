package chess.domain.piece;

import chess.domain.player.Player;

public class Queen extends ChessPiece {

    private static final String NAME = "Q";

    public Queen(Player player) {
        super(NAME, player);
    }
}
