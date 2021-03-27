package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.moving.KnightMoving;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.WrongInitPositionException;

public final class Knight extends NoKingPieces {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 2.5;
    private static final int LEFT_SIDE_INIT_COL = 1;
    private static final int RIGHT_SIDE_INIT_COL = 6;

    public Knight(final Team team, final Position position) {
        super(position, "N", team, SCORE, new KnightMoving());
    }

    public static Knight of(final Team team, final int col) {
        if (col != LEFT_SIDE_INIT_COL && col != RIGHT_SIDE_INIT_COL) {
            throw new WrongInitPositionException();
        }
        return new Knight(team, initPosition(team, col));
    }

    private static Position initPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.location(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.location(WHITE_TEAM_ROW), col);
    }
}
