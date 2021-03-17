package chess.domain.piece;

import chess.domain.player.TeamType;

public class Bishop extends Piece {
    private static final String NAME = "B";

    public Bishop(TeamType teamType) {
        super(teamType, NAME);
    }
}
