package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {
    private static final String BLACK_TEAM_ROW = "7";
    private static final String WHITE_TEAM_ROW = "2";

    public Pawn(final Team team, final Position position) {
        super(position, "P", team);
    }

    public static Pawn of(final Team team, final int col) {
        return new Pawn(team, getInitPosition(team, col));
    }

    private static Position getInitPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.getLocation(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.getLocation(WHITE_TEAM_ROW), col);
    }

    @Override
    public List<Position> getMovablePositions(final Map<Team, Pieces> board) {
        List<Position> movablePositions = new ArrayList<>();
        movablePositions.addAll(getAttackablePositions(board));
        return null;
    }

    private List<Position> getAttackablePositions(final Map<Team, Pieces> board) {
        List<Position> attackablePositions = new ArrayList<>();
        Team myTeam = super.getTeam();

        int dx[] = {-1, -1};
        int dy[] = {-1, 1};
        if (myTeam.equals(Team.WHITE)) {
            Position curPosition = super.getPosition();

            for (int dir = 0; dir < dx.length; ++dir) {
                int nx = curPosition.getRow() + dx[dir];
                int ny = curPosition.getCol() + dy[dir];
                if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) continue;

                Position attackPosition = new Position(nx, ny);

                //List<Piece> otherTeamPieces = board.get(Team.BLACK);

//                for (Piece piece : otherTeamPieces) {
//                    if (piece.samePosition(attackPosition)) {
//                        attackablePositions.add(attackPosition);
//                    }
//                }
            }
        }
        return attackablePositions;
    }


}
