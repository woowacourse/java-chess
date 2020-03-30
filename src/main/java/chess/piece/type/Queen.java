package chess.piece.type;

import chess.location.Location;
import chess.score.Score;
import chess.team.Team;

public class Queen extends Piece {
    private static final char NAME = 'q';
    private static final int QUEEN_SCORE = 9;

    public Queen(Team team) {
        super(NAME, new Score(QUEEN_SCORE), team);
    }

    @Override
    public boolean canMove(Location now, Location after) {
        return now.isQueenRang(after);
    }
}
