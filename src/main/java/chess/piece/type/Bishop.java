package chess.piece.type;

import chess.piece.type.movable.BishopMovable;
import chess.score.Score;
import chess.team.Team;

public class Bishop extends Piece {
    private static final char NAME = 'b';
    private static final Score SCORE = new Score(3);

    public Bishop(Team team) {
        super(NAME, SCORE, team, new BishopMovable());
    }
}

