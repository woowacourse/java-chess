package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";

    public King(final Team team, final Position position) {
        super(position, "K", team);
    }

    public static King of(final Team team, final int col) {
        if (col != 4) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new King(team, getInitPosition(team, col));
    }

    private static Position getInitPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.getLocation(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.getLocation(WHITE_TEAM_ROW), col);
    }

    @Override
    public List<Position> getMovablePositions(final Board board) {
        List<Position> movablePositions = new ArrayList<>();

        int[] rowDir = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] colDir = {-1, 1, 0, 0, -1, 1, 1, -1};

        for (int dir = 0; dir < colDir.length; ++dir) {
            addMovablePositions(movablePositions, board, rowDir[dir], colDir[dir]);
        }
        return movablePositions;
    }

    private void addMovablePositions(List<Position> movablePositions, Board board, int rowDir, int colDir) {
        int curRow = getPosition().getRow();
        int curCol = getPosition().getCol();

        if (isMoveAbleDir(board, curRow + rowDir, curCol + colDir)) {
            movablePositions.add(new Position(curRow + rowDir, curCol + colDir));
        }
    }

    private boolean isMoveAbleDir(Board board, int nextRow, int nextCol) {
        if (!board.validateRange(nextRow, nextCol)) {
            return false;
        }
        return !board.piecesByTeam(getTeam()).containByPosition(new Position(nextRow, nextCol));
    }
}
