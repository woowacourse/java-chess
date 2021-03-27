package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.moving.PawnMoving;
import chess.domain.position.Position;
import chess.domain.position.Row;

public final class Pawn extends NoKingPieces {
    private static final String BLACK_TEAM_ROW = "7";
    private static final String WHITE_TEAM_ROW = "2";
    private static final double SCORE = 1.0;

    public Pawn(final Team team, final Position position) {
        super(position, "P", team, SCORE, new PawnMoving());
    }

    public static Pawn of(final Team team, final int col) {
        return new Pawn(team, initPosition(team, col));
    }

    private static Position initPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.location(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.location(WHITE_TEAM_ROW), col);
    }

    @Override
    public final boolean isPawn() {
        return true;
    }
}
