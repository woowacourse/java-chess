package chess.domain.piece;

import chess.domain.player.TeamType;

public class King extends Piece {
    private static final String NAME = "K";

    public King(TeamType teamType) {
        super(teamType, NAME);
    }
}
