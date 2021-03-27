package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.move.MultiMoving;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.WrongInitPositionException;

import java.util.List;

public final class Queen extends NoKingPieces {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 9.0;
    private static final int INIT_COL = 3;

    public Queen(final Team team, final Position position) {
        super(position, "Q", team, SCORE, new MultiMoving());
    }

    public static Queen of(final Team team, final int col) {
        if (col != INIT_COL) {
            throw new WrongInitPositionException();
        }
        return new Queen(team, initPosition(team, col));
    }

    private static Position initPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.location(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.location(WHITE_TEAM_ROW), col);
    }

    @Override
    public final List<Position> allMovablePositions(final Board board) {
        int[] rowDirection = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] colDirection = {-1, 1, 0, 0, -1, 1, 1, -1};
        return moving().movablePositions(this, board, rowDirection, colDirection);
    }
}
