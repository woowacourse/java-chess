package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class Bishop extends Piece {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";

    public Bishop(final Team team, final Position position) {
        super(position, "B", team);
    }

    public static Bishop of(final Team team, final int col) {
        if (col != 2 && col != 5) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new Bishop(team, getInitPosition(team, col));
    }

    private static Position getInitPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.getLocation(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.getLocation(WHITE_TEAM_ROW), col);
    }
}
