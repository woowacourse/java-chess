package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Movable.SingleMove;
import chess.domain.position.Position;
import chess.domain.util.ColumnConverter;
import chess.domain.util.RowConverter;

import java.util.ArrayList;
import java.util.List;

public final class Knight extends NoKingPieces implements SingleMove {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 2.5;
    private static final int LEFT_SIDE_INIT_COLUMN = 1;
    private static final int RIGHT_SIDE_INIT_COLUMN = 6;

    private Knight(final Team team, final Position position) {
        super(position, "N", team, SCORE);
    }

    private Knight(final Position position, final String initial, final Team team, final double score) {
        super(position, initial, team, score);
    }

    public static Knight of(final Team team, final Position position) {
        return new Knight(position, "N", team, SCORE);
    }

    public static Knight of(final Team team, final int column) {
        if (column != LEFT_SIDE_INIT_COLUMN && column != RIGHT_SIDE_INIT_COLUMN) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new Knight(team, getInitPosition(team, column));
    }

    private static Position getInitPosition(final Team team, final int column) {
        if (team.equals(Team.BLACK)) {
            return new Position(RowConverter.getLocation(BLACK_TEAM_ROW), column);
        }
        return new Position(RowConverter.getLocation(WHITE_TEAM_ROW), column);
    }

    public static List<Knight> getInitKnights(final Team team) {
        List<Knight> knights = new ArrayList<>();
        ColumnConverter.getKnightInitCols().forEach((column) -> knights.add(Knight.of(team, column)));
        return knights;
    }

    @Override
    public List<Position> getMovablePositions(final Board board) {
        int[] rowDirection = {-1, 1, 2, 2, 1, -1, -2, -2};
        int[] columnDirection = {2, 2, 1, -1, -2, -2, -1, 1};
        return getMovablePositionsByDirection(board, rowDirection, columnDirection);
    }

    @Override
    public boolean isMoveAble(final List<Position> movablePositions, final Board board, final int nextRow, final int nextColumn) {
        return isMoveAbleDirection(board, nextRow, nextColumn, getTeam());
    }
}
