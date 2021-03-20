package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.player.TeamType;

public class Rook extends Piece {
    private static final String NAME = "R";
    private static final double SCORE = 5;

    public Rook(TeamType teamType) {
        super(teamType, NAME, SCORE, Direction.getRookDirections());
    }
}
