package chess.piece.type;

import chess.location.Location;
import chess.score.Score;
import chess.team.Team;

public class Bishop extends Piece {
    private static final char NAME = 'b';
    private static final int BISHOP_SCORE = 3;

    public Bishop(Team team) {
        super(changeName(team), new Score(BISHOP_SCORE));
    }

    private static char changeName(Team team) {
        if (team.isBlack()) {
            return Character.toUpperCase(NAME);
        }
        return NAME;
    }

    @Override
    public boolean canMove(Location now, Location after) {
        return now.isDiagonal(after);
    }
}

