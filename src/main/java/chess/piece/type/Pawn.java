package chess.piece.type;

import chess.piece.type.movable.PawnPieceMovable;
import chess.score.Score;
import chess.team.Team;

public class Pawn extends Piece {
    private static final char NAME = 'p';
    private static final int SCORE = 1;

    public Pawn(Team team) {
        super(NAME, new Score(SCORE), team, new PawnPieceMovable(team));
    }
}
