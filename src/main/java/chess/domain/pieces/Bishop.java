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
    private static final int LEFT_SIDE_INIT_COLUMN = 2;
    private static final int RIGHT_SIDE_INIT_COLUMN = 5;

    private Bishop(final Team team, final Position position) {
        super(position, "B", team, SCORE);
    }

    private Bishop(final Position position, final String initial, final Team team, final double score) {
        super(position, initial, team, score);
    }

    public static Bishop of(final Team team, final Position position) {
        return new Bishop(position, "B", team, SCORE);
    }

    public static Bishop of(final Team team, final int column) {
        if (column != LEFT_SIDE_INIT_COLUMN && column != RIGHT_SIDE_INIT_COLUMN) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new Bishop(team, getInitPosition(team, column));
    }

    private static Position getInitPosition(final Team team, final int column) {
        if (team.equals(Team.BLACK)) {
            return new Position(RowConverter.getLocation(BLACK_TEAM_ROW), column);
        }
        return new Position(RowConverter.getLocation(WHITE_TEAM_ROW), column);
    }

    public static List<Bishop> getInitBishop(final Team team) {
        List<Bishop> bishops = new ArrayList<>();
        ColumnConverter.getBishopInitCols().forEach((column) -> bishops.add(Bishop.of(team, column)));
        return bishops;
    }

    @Override
    public List<Position> getMovablePositions(final Board board) {
        int[] rowDirection = {-1, 1, -1, 1};
        int[] columnDirection = {-1, 1, 1, -1};
        return getMovablePositionsByDirection(board, rowDirection, columnDirection);
    }

    @Override
    protected void addMovablePositions(final List<Position> movablePositions, final Board board, final int rowDirection, final int colDirection) {
        int currentRow = getPosition().getRow();
        int currentColumn = getPosition().getColumn();

        while (isMoveAble(movablePositions, board, currentRow + rowDirection, currentColumn + colDirection)) {
            movablePositions.add(new Position(currentRow += rowDirection, currentColumn += colDirection));
        }
    }

    @Override
    protected boolean isMoveAble(final List<Position> movablePositions, final Board board, final int nextRow, final int nextColumn) {
        return isMoveAbleDirection(movablePositions, board, nextRow, nextColumn, getTeam());
    }
}
