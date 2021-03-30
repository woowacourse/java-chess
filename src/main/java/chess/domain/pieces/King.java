package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Movable.SingleMove;
import chess.domain.position.Position;
import chess.domain.util.ColumnConverter;
import chess.domain.util.RowConverter;

import java.util.ArrayList;
import java.util.List;

public final class King extends Piece implements SingleMove {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 0.0;
    private static final int INIT_COLUMN = 4;

    private King(final Team team, final Position position) {
        super(position, "K", team, SCORE);
    }

    private King(final Position position, final String initial, final Team team, final double score) {
        super(position, initial, team, score);
    }

    public static King of(final Team team, final Position position) {
        return new King(position, "K", team, SCORE);
    }

    public static King of(final Team team, final int column) {
        if (column != INIT_COLUMN) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new King(team, getInitPosition(team, column));
    }

    private static Position getInitPosition(final Team team, final int column) {
        if (team.equals(Team.BLACK)) {
            return new Position(RowConverter.getLocation(BLACK_TEAM_ROW), column);
        }
        return new Position(RowConverter.getLocation(WHITE_TEAM_ROW), column);
    }

    public static List<King> getInitKing(final Team team) {
        List<King> kings = new ArrayList<>();
        ColumnConverter.getKingInitCols().forEach((column) -> kings.add(King.of(team, column)));
        return kings;
    }

    @Override
    public List<Position> getMovablePositions(final Board board) {
        int[] rowDirection = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] columnDirection = {-1, 1, 0, 0, -1, 1, 1, -1};

        return getMovablePositionsByDirection(board, rowDirection, columnDirection);
    }

    @Override
    public boolean isMoveAble(final List<Position> movablePositions, final Board board, final int nextRow, final int nextColumn) {
        return isMoveAbleDirection(board, nextRow, nextColumn, getTeam());
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
