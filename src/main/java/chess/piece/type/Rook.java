package chess.piece.type;

import chess.piece.type.movable.RookPieceMovable;
import chess.score.Score;
import chess.team.Team;

public class Rook extends Piece {
    private static final char NAME = 'r';
    private static final Score SCORE = new Score(5);

    public Rook(Team team) {
        super(NAME, SCORE, team, new RookPieceMovable());
    }
}
