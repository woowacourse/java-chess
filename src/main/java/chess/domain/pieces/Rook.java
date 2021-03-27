package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.move.MultiMoving;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.exception.WrongInitPositionException;

import java.util.List;

public final class Rook extends NoKingPieces {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 5.0;
    private static final int LEFT_SIDE_INIT_COL = 0;
    private static final int RIGHT_SIDE_INIT_COL = 7;

    public Rook(final Team team, final Position position) {
        super(position, "R", team, SCORE, new MultiMoving());
    }

    public static Rook of(final Team team, final int col) {
        if (col != LEFT_SIDE_INIT_COL && col != RIGHT_SIDE_INIT_COL) {
            throw new WrongInitPositionException();
        }
        return new Rook(team, initPosition(team, col));
    }

    private static Position initPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.location(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.location(WHITE_TEAM_ROW), col);
    }

    @Override
    public final List<Position> allMovablePositions(final Board board) {
        int[] rowDirection = {0, 0, -1, 1};
        int[] colDirection = {-1, 1, 0, 0};
        return moving().movablePositions(this, board, rowDirection, colDirection);
    }
}
