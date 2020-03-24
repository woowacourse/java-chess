package chess.domain.piece;

import chess.domain.player.Player;

public class Pawn extends ChessPiece {

    private static final String NAME = "P";

    public Pawn(Player player) {
        super(NAME, player);
    }
}
