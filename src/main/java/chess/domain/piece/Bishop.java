package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.player.TeamType;

public class Bishop extends Piece {
    private static final String NAME = "B";
    private static final double SCORE = 3;

    public Bishop(TeamType teamType) {
        super(teamType, NAME, SCORE, Direction.getBishopDirections());
    }
}
