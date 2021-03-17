package chess.domain.piece;

import chess.domain.player.PlayerType;

public class Pawn extends Piece {
    private static final String NAME = "P";

    public Pawn(PlayerType playerType) {
        super(playerType, NAME);
    }
}
