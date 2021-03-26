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
    private static final int INIT_COL = 4;

    private King(final Team team, final Position position) {
        super(position, "K", team, SCORE);
    }

    private King(final Position position, final String initial, final Team team, final double score) {
        super(position, initial, team, score);
    }

    public static King of(final Team team, final Position position) {
        return new King(position, "K", team, SCORE);
    }

    public static King of(final Team team, final int col) {
        if (col != INIT_COL) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new King(team, getInitPosition(team, col));
    }

    private static Position getInitPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(RowConverter.getLocation(BLACK_TEAM_ROW), col);
        }
        return new Position(RowConverter.getLocation(WHITE_TEAM_ROW), col);
    }

    public static List<King> getInitKing(final Team team) {
        List<King> kings = new ArrayList<>();
        ColumnConverter.getKingInitCols().forEach((col) -> kings.add(King.of(team, col)));
        return kings;
    }

    @Override
    public List<Position> getMovablePositions(Board board) {
        int[] rowDirection = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] colDirection = {-1, 1, 0, 0, -1, 1, 1, -1};

        return getMovablePositionsByDir(board, rowDirection, colDirection);
    }

    @Override
    public boolean isMoveAble(List<Position> movablePositions, Board board, int nextRow, int nextCol) {
        return isMoveAbleDir(board, nextRow, nextCol, getTeam());
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
