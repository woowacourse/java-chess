package chess.domain.piece;

import chess.domain.player.PlayerType;

public class Rook extends Piece {
    private static final String NAME = "R";

    public Rook(PlayerType playerType) {
        super(playerType, NAME);
    }
}
