package chess.domain.piece;

import chess.domain.player.TeamType;

public class Knight extends Piece {
    private static final String NAME = "N";

    public Knight(TeamType teamType) {
        super(teamType, NAME);
    }
}
