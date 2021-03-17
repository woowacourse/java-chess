package chess.domain.piece;

import chess.domain.player.TeamType;

public class Queen extends Piece {
    private static final String NAME = "Q";

    public Queen(TeamType teamType) {
        super(teamType, NAME);
    }
}
