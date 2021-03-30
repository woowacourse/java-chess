package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Movable.MultiMove;
import chess.domain.position.Position;
import chess.domain.util.ColumnConverter;
import chess.domain.util.RowConverter;

import java.util.ArrayList;
import java.util.List;

public final class Rook extends NoKingPieces implements MultiMove {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 5.0;
    private static final int LEFT_SIDE_INIT_COLUMN = 0;
    private static final int RIGHT_SIDE_INIT_COLUMN = 7;

    private Rook(final Team team, final Position position) {
        super(position, "R", team, SCORE);
    }

    private Rook(final Position position, final String initial, final Team team, final double score) {
        super(position, initial, team, score);
    }

    public static Rook of(final Team team, final Position position) {
        return new Rook(position, "R", team, SCORE);
    }

    public static Rook of(final Team team, final int column) {
        if (column != LEFT_SIDE_INIT_COLUMN && column != RIGHT_SIDE_INIT_COLUMN) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new Rook(team, getInitPosition(team, column));
    }

    private static Position getInitPosition(final Team team, final int column) {
        if (team.equals(Team.BLACK)) {
            return new Position(RowConverter.getLocation(BLACK_TEAM_ROW), column);
        }
        return new Position(RowConverter.getLocation(WHITE_TEAM_ROW), column);
    }

    public static List<Rook> getInitRooks(final Team team) {
        List<Rook> rooks = new ArrayList<>();
        ColumnConverter.getRookInitCols().forEach((column) -> rooks.add(Rook.of(team, column)));
        return rooks;
    }

    @Override
    public List<Position> getMovablePositions(final Board board) {
        int[] rowDirection = {0, 0, -1, 1};
        int[] columnDirection = {-1, 1, 0, 0};
        return getMovablePositionsByDirection(board, rowDirection, columnDirection);
    }

    @Override
    public boolean isMoveAble(final List<Position> movablePositions, final Board board, final int nextRow, final int nextColumn) {
        return isMoveAbleDirection(movablePositions, board, nextRow, nextColumn, getTeam());
    }
}
