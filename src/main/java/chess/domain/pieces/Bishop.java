package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Movable.MultiMove;
import chess.domain.position.Position;
import chess.domain.util.ColumnConverter;
import chess.domain.util.RowConverter;

import java.util.ArrayList;
import java.util.List;

public final class Bishop extends NoKingPieces implements MultiMove {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 3.0;
    private static final int LEFT_SIDE_INIT_COL = 2;
    private static final int RIGHT_SIDE_INIT_COL = 5;

    private Bishop(final Team team, final Position position) {
        super(position, "B", team, SCORE);
    }

    private Bishop(final Position position, final String initial, final Team team, final double score) {
        super(position, initial, team, score);
    }

    public static Bishop of(final Team team, final Position position) {
        return new Bishop(position, "B", team, SCORE);
    }

    public static Bishop of(final Team team, final int col) {
        if (col != LEFT_SIDE_INIT_COL && col != RIGHT_SIDE_INIT_COL) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new Bishop(team, getInitPosition(team, col));
    }

    private static Position getInitPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(RowConverter.getLocation(BLACK_TEAM_ROW), col);
        }
        return new Position(RowConverter.getLocation(WHITE_TEAM_ROW), col);
    }

    public static List<Bishop> getInitBishop(final Team team) {
        List<Bishop> bishops = new ArrayList<>();
        ColumnConverter.getBishopInitCols().forEach((col) -> bishops.add(Bishop.of(team, col)));
        return bishops;
    }

    @Override
    public List<Position> getMovablePositions(final Board board) {
        int[] rowDir = {-1, 1, -1, 1};
        int[] colDir = {-1, 1, 1, -1};
        return getMovablePositionsByDir(board, rowDir, colDir);
    }

    @Override
    public boolean isMoveAble(final List<Position> movablePositions, final Board board, final int nextRow, final int nextCol) {
        return isMoveAbleDir(movablePositions, board, nextRow, nextCol, getTeam());
    }
}
