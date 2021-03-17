package chess.domain.piece;

import chess.domain.player.TeamType;

public class Rook extends Piece {
    private static final String NAME = "R";

    public Rook(TeamType teamType) {
        super(teamType, NAME);
    }
}
