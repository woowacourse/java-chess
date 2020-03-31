package chess.piece.type;

import chess.piece.type.movable.KnightPieceMovable;
import chess.score.Score;
import chess.team.Team;

public class Knight extends Piece {
    private static final char NAME = 'n';
    private static final double SCORE = 2.5;

    public Knight(Team team) {
        super(NAME, new Score(SCORE), team, new KnightPieceMovable());
    }
}
