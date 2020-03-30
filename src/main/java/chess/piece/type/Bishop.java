package chess.piece.type;

import chess.location.Location;
import chess.score.Score;
import chess.team.Team;

public class Bishop extends Piece {
    private static final char NAME = 'b';
    private static final int BISHOP_SCORE = 3;

    public Bishop(Team team) {
        super(NAME, new Score(BISHOP_SCORE), team);
    }

    @Override
    public boolean canMove(Location now, Location after) {
        return now.isDiagonal(after);
    }
}

