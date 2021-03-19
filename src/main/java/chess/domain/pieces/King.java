package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.List;
import java.util.Map;

public class King extends Piece {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";

    public King(final Team team, final Position position) {
        super(position, "K", team);
    }

    public static King of(final Team team, final int col) {
        if (col != 4) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new King(team, getInitPosition(team, col));
    }

    private static Position getInitPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.getLocation(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.getLocation(WHITE_TEAM_ROW), col);
    }

    @Override
    public List<Position> getMovablePositions(Map<Team, Pieces> board) {
        return null;
    }
}
