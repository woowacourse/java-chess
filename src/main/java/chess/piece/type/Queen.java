package chess.piece.type;

import chess.board.Location;
import chess.score.Score;
import chess.team.Team;

public class Queen extends Piece {
    private static final char name = 'q';
    private static final int QUEEN_SCORE = 9;

    public Queen(Team team) {
        super(changeName(team), new Score(QUEEN_SCORE));
    }

    private static char changeName(Team team) {
        if (team.isBlack()) {
            return Character.toUpperCase(name);
        }
        return name;
    }

    @Override
    public boolean canMove(Location now, Location after) {
        return now.isQueenRang(after);
    }
}
