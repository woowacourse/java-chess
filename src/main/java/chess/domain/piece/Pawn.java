package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import chess.domain.piece.direction.PawnDirections;
import chess.domain.piece.moving.PawnMoving;

public class Pawn extends Piece {

    private static final Score HALF_SCORE = Score.from(0.5);

    public Pawn(TeamColor teamColor, Position position) {
        super("p", teamColor, Score.from(1), new PawnMoving(new PawnDirections(teamColor), position));
    }

    public static Score halfScore() {
        return HALF_SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
