package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Movable.MultiMove;
import chess.domain.position.Position;
import chess.domain.util.RowConverter;

import java.util.List;

public final class Rook extends NoKingPieces implements MultiMove {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 5.0;
    private static final int LEFT_SIDE_INIT_COL = 0;
    private static final int RIGHT_SIDE_INIT_COL = 7;

    private Rook(final Team team, final Position position) {
        super(position, "R", team, SCORE);
    }

    private Rook(final Position position, final String initial, final Team team, final double score) {
        super(position, initial, team, score);
    }

    public static Rook of(final Team team, final Position position) {
        return new Rook(position, "R", team, SCORE);
    }

    public static Rook of(final Team team, final int col) {
        if (col != LEFT_SIDE_INIT_COL && col != RIGHT_SIDE_INIT_COL) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new Rook(team, getInitPosition(team, col));
    }

    private static Position getInitPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(RowConverter.getLocation(BLACK_TEAM_ROW), col);
        }
        return new Position(RowConverter.getLocation(WHITE_TEAM_ROW), col);
    }

    @Override
    public List<Position> getMovablePositions(final Board board) {
        int[] rowDirection = {0, 0, -1, 1};
        int[] colDirection = {-1, 1, 0, 0};
        return getMovablePositionsByDir(board, rowDirection, colDirection);
    }

    @Override
    public boolean isMoveAble(final List<Position> movablePositions, final Board board, final int nextRow, final int nextCol) {
        return isMoveAbleDir(movablePositions, board, nextRow, nextCol, getTeam());
    }
}
