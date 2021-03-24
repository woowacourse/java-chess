package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.move.PawnMove;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    private static final String BLACK_TEAM_ROW = "7";
    private static final String WHITE_TEAM_ROW = "2";
    private static final double SCORE = 1.0;

    public Pawn(final Team team, final Position position) {
        super(position, "P", team, SCORE, new PawnMove());
    }

    public static Pawn of(final Team team, final int col) {
        return new Pawn(team, getInitPosition(team, col));
    }

    private static Position getInitPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(Row.getLocation(BLACK_TEAM_ROW), col);
        }
        return new Position(Row.getLocation(WHITE_TEAM_ROW), col);
    }

    @Override
    public final List<Position> getMovablePositions(final Board board) {
        List<Position> movablePositions = new ArrayList<>();
        addAttackablePositions(movablePositions, board);
        addMovablePositions(movablePositions, board, 1);

        if (getPosition().isInitPawnPositionByTeam(getTeam())) {
            addMovablePositions(movablePositions, board, 2);
        }
        return movablePositions;
    }

    private void addMovablePositions(final List<Position> movablePositions, final Board board, final int degree) {
        Position curPosition = getPosition();
        if (!board.validateRange(new Position(curPosition.getRow() + getStraightRow(degree), curPosition.getCol()))) {
            return;
        }
        Position movedPosition = new Position(curPosition.getRow() + getStraightRow(degree), curPosition.getCol());

        if (!board.piecesByTeam(Team.BLACK).containByPosition(movedPosition) && !board.piecesByTeam(Team.WHITE).containByPosition(movedPosition)) {
            movablePositions.add(movedPosition);
        }
    }

    private int getStraightRow(final int degree) {
        if (getTeam() == Team.BLACK) {
            return degree;
        }
        return -degree;
    }

    private void addAttackablePositions(final List<Position> movablePositions, final Board board) {
        Team myTeam = getTeam();
        if (myTeam.equals(Team.WHITE)) {
            movablePositions.addAll(addWhiteTeamAttackPosition(board));
            return;
        }
        movablePositions.addAll(addBlackTeamAttackPosition(board));
    }

    private List<Position> addBlackTeamAttackPosition(final Board board) {
        int[] rowDir = {1, 1};
        int[] colDir = {-1, 1};
        return movable().allMovablePosition(this, board, rowDir, colDir);
    }

    private List<Position> addWhiteTeamAttackPosition(final Board board) {
        int[] rowDir = {-1, -1};
        int[] colDir = {-1, 1};
        return movable().allMovablePosition(this, board, rowDir, colDir);
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
