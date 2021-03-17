package chess.domain.piece;

import chess.domain.player.TeamType;

public class Pawn extends Piece {
    private static final String NAME = "P";

    public Pawn(TeamType teamType) {
        super(teamType, NAME);
    }
}
