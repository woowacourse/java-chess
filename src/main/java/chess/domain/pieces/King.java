package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.moving.KingMoving;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.WrongInitPositionException;

public final class King extends Piece {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 0.0;
    private static final int INIT_COL = 4;

    public King(final Team team, final Position position) {
        super(position, "K", team, SCORE, new KingMoving());
    }

    public static King of(final Team team, final int col) {
        if (col != INIT_COL) {
            throw new WrongInitPositionException();
        }
        return new King(team, initPosition(team, col));
    }

    private static Position initPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.location(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.location(WHITE_TEAM_ROW), col);
    }

    @Override
    public final boolean isKing() {
        return true;
    }

    @Override
    public final boolean isPawn() {
        return false;
    }
}
