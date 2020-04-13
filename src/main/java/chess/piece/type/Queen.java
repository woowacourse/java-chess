package chess.piece.type;

import chess.piece.type.movable.QueenPieceMovable;
import chess.score.Score;
import chess.team.Team;

public class Queen extends Piece {
    private static final char NAME = 'q';
    private static final Score SCORE = new Score(9);

    public Queen(Team team) {
        super(NAME, SCORE, team, new QueenPieceMovable());
    }
}
