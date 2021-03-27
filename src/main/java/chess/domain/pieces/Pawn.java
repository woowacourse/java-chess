package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.move.PawnMoving;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    private static final String BLACK_TEAM_ROW = "7";
    private static final String WHITE_TEAM_ROW = "2";
    private static final double SCORE = 1.0;

    public Pawn(final Team team, final Position position) {
        super(position, "P", team, SCORE, new PawnMoving());
    }

    public static Pawn of(final Team team, final int col) {
        return new Pawn(team, initPosition(team, col));
    }

    private static Position initPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.location(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.location(WHITE_TEAM_ROW), col);
    }

    @Override
    public final List<Position> allMovablePositions(final Board board) {
        List<Position> movablePositions = new ArrayList<>();
        addAttackablePositions(movablePositions, board);
        addStraightPosition(movablePositions, board, 1);

        if (position().isSameInitPawnPositionByTeam(team())) {
            addStraightPosition(movablePositions, board, 2);
        }
        return movablePositions;
    }

    private void addAttackablePositions(final List<Position> movablePositions, final Board board) {
        Team myTeam = team();
        if (myTeam.equals(Team.WHITE)) {
            movablePositions.addAll(whiteTeamAttackPosition(board));
            return;
        }
        movablePositions.addAll(blackTeamAttackPosition(board));
    }

    private List<Position> whiteTeamAttackPosition(final Board board) {
        int[] rowDirection = {-1, -1};
        int[] colDirection = {-1, 1};
        return moving().movablePositions(this, board, rowDirection, colDirection);
    }

    private List<Position> blackTeamAttackPosition(final Board board) {
        int[] rowDirection = {1, 1};
        int[] colDirection = {-1, 1};
        return moving().movablePositions(this, board, rowDirection, colDirection);
    }

    private void addStraightPosition(final List<Position> movablePositions, final Board board, final int degree) {
        Position currentPosition = position();
        Position nextPosition = currentPosition.next(straightRow(degree), 0);
        if (!board.validatesPieceWithinBoardRange(nextPosition)) {
            return;
        }

        if (!board.piecesByTeam(Team.BLACK).containsPosition(nextPosition) && !board.piecesByTeam(Team.WHITE).containsPosition(nextPosition)) {
            movablePositions.add(nextPosition);
        }
    }

    private int straightRow(final int degree) {
        if (team() == Team.BLACK) {
            return degree;
        }
        return -degree;
    }

    @Override
    public final boolean isKing() {
        return false;
    }

    @Override
    public final boolean isPawn() {
        return true;
    }
}
