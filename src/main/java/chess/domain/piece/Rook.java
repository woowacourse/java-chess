package chess.domain.piece;

import chess.domain.player.Player;

public class Rook extends ChessPiece {

    private static final String NAME = "R";

    public Rook(Player player) {
        super(NAME, player);
    }
}
