package chess.db.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.PAWN;

import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;

public class PawnEntity extends PieceEntity {
    private static final double DEFAULT_SCORE = 1;
    private static final double HALF_SCORE = DEFAULT_SCORE / 2;

    public PawnEntity(Long id, TeamColor teamColor) {
        super(id, PAWN, teamColor, DEFAULT_SCORE, Direction.pawnDirections(teamColor));
    }

    public PawnEntity(TeamColor teamColor) {
        super(PAWN, teamColor, DEFAULT_SCORE, Direction.pawnDirections(teamColor));
    }

    public static double defaultScore() {
        return DEFAULT_SCORE;
    }

    public static double halfScore() {
        return HALF_SCORE;
    }
}
