package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.RowConverter;

import java.util.ArrayList;
import java.util.List;

public final class Knight extends NoKingPieces {
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
    public final List<Position> getMovablePositions(final Board board) {
        List<Position> movablePositions = new ArrayList<>();

        int[] rowDirection = {-1, 1, 2, 2, 1, -1, -2, -2};
        int[] colDirection = {2, 2, 1, -1, -2, -2, -1, 1};

        for (int dir = 0; dir < colDirection.length; ++dir) {
            addMovablePositions(movablePositions, board, rowDirection[dir], colDirection[dir]);
        }
        return movablePositions;
    }

    private void addMovablePositions(final List<Position> movablePositions, final Board board, final int rowDir, final int colDir) {
        int curRow = getPosition().getRow();
        int curCol = getPosition().getCol();

        if (isMoveAbleDir(board, curRow + rowDir, curCol + colDir)) {
            movablePositions.add(new Position(curRow + rowDir, curCol + colDir));
        }
    }

    private boolean isMoveAbleDir(final Board board, final int nextRow, final int nextCol) {
        if (!board.validateRange(nextRow, nextCol)) {
            return false;
        }
        return !board.piecesByTeam(getTeam()).containByPosition(new Position(nextRow, nextCol));
    }
}
