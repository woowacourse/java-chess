package chess.piece.type;

import chess.location.Location;
import chess.score.Score;
import chess.team.Team;

public class Rook extends Piece {
    private static final char NAME = 'r';
    private static final int ROOK_SCORE = 5;

    public Rook(Team team) {
        super(NAME, new Score(ROOK_SCORE), team);
    }

    @Override
    public boolean canMove(Location now, Location after) {
        return now.isStraight(after);
    }
}
