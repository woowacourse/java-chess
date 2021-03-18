package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class Rook extends Piece {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";

    public Rook(final Team team, final Position position) {
        super(position, "R", team);
    }

    public static Rook of(final Team team, final int col) {
        if (col != 0 && col != 7) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new Rook(team, getInitPosition(team, col));
    }

    private static Position getInitPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.getLocation(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.getLocation(WHITE_TEAM_ROW), col);
    }
}
