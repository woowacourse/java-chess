package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Movable.SingleMove;
import chess.domain.position.Position;
import chess.domain.util.RowConverter;

import java.util.List;

public final class Knight extends NoKingPieces implements SingleMove {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 2.5;
    private static final int LEFT_SIDE_INIT_COL = 1;
    private static final int RIGHT_SIDE_INIT_COL = 6;

    private Knight(final Team team, final Position position) {
        super(position, "N", team, SCORE);
    }

    private Knight(final Position position, final String initial, final Team team, final double score) {
        super(position, initial, team, score);
    }

    public static Knight of(final Team team, final Position position) {
        return new Knight(position, "N", team, SCORE);
    }

    public static Knight of(final Team team, final int col) {
        if (col != LEFT_SIDE_INIT_COL && col != RIGHT_SIDE_INIT_COL) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new Knight(team, getInitPosition(team, col));
    }

    private static Position getInitPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(RowConverter.getLocation(BLACK_TEAM_ROW), col);
        }
        return new Position(RowConverter.getLocation(WHITE_TEAM_ROW), col);
    }

    @Override
    public List<Position> getMovablePositions(Board board) {
        int[] rowDirection = {-1, 1, 2, 2, 1, -1, -2, -2};
        int[] colDirection = {2, 2, 1, -1, -2, -2, -1, 1};
        return getMovablePositionsByDir(board, rowDirection, colDirection);
    }

    @Override
    public boolean isMoveAble(List<Position> movablePositions, Board board, int nextRow, int nextCol) {
        return isMoveAbleDir(board, nextRow, nextCol, getTeam());
    }
}
