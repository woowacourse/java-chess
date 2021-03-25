package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.RowConverter;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    private static final String BLACK_TEAM_ROW = "7";
    private static final String WHITE_TEAM_ROW = "2";
    private static final double SCORE = 1.0;

    private Pawn(final Team team, final Position position) {
        super(position, "P", team, SCORE);
    }

    private Pawn(final Position position, final String initial, final Team team, final double score) {
        super(position, initial, team, score);
    }

    public static Pawn of(final Team team, final Position position) {
        return new Pawn(position, "P", team, SCORE);
    }

    public static Pawn of(final Team team, final int col) {
        return new Pawn(team, getInitPosition(team, col));
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
        addAttackablePositions(movablePositions, board);
        addMovablePositions(movablePositions, board, 1);

        if (getPosition().isInitPositionByTeam(getTeam())) {
            addMovablePositions(movablePositions, board, 2);
        }
        return movablePositions;
    }

    @Override
    public final boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    private void addMovablePositions(final List<Position> movablePositions, final Board board, final int degree) {
        Position curPosition = getPosition();
        if (!board.validateRange(curPosition.getRow() + getStraightRow(degree), curPosition.getCol())) {
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
            addWhiteTeamAttackPosition(movablePositions, board);
            return;
        }
        addBlackTeamAttackPosition(movablePositions, board);
    }

    private void addBlackTeamAttackPosition(final List<Position> movablePositions, final Board board) {
        int[] rowDir = {1, 1};
        int[] colDir = {-1, 1};
        checkAttackPosition(movablePositions, board, rowDir, colDir);
    }

    private void addWhiteTeamAttackPosition(final List<Position> movablePositions, final Board board) {
        int[] rowDir = {-1, -1};
        int[] colDir = {-1, 1};
        checkAttackPosition(movablePositions, board, rowDir, colDir);
    }

    private void checkAttackPosition(final List<Position> movablePositions, final Board board, final int[] rowDir, final int[] colDir) {
        Position curPosition = getPosition();
        for (int dir = 0; dir < rowDir.length; ++dir) {
            int nextRow = curPosition.getRow() + rowDir[dir];
            int nextCol = curPosition.getCol() + colDir[dir];
            addAttackablePosition(movablePositions, board, nextRow, nextCol);
        }
    }

    private void addAttackablePosition(final List<Position> movablePositions, Board board, final int nextRow, final int nextCol) {
        if (!board.validateRange(nextRow, nextCol)) {
            return;
        }

        Position attackPosition = new Position(nextRow, nextCol);
        Pieces otherTeamPieces = board.piecesByTeam(Team.getAnotherTeam(getTeam()));

        if (otherTeamPieces.containByPosition(attackPosition)) {
            movablePositions.add(attackPosition);
        }
    }
}
