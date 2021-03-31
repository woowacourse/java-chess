package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.ColumnConverter;
import chess.domain.util.RowConverter;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends NoKingPieces {
    private static final String BLACK_TEAM_ROW = "7";
    private static final String WHITE_TEAM_ROW = "2";
    private static final double SCORE = 1.0;
    public static final int SINGLE_MOVE_DEGREE = 1;
    public static final int DOUBLE_MOVE_DEGREE = 2;

    private Pawn(final Team team, final Position position) {
        super(position, "P", team, SCORE);
    }

    private Pawn(final Position position, final String initial, final Team team, final double score) {
        super(position, initial, team, score);
    }

    public static Pawn of(final Team team, final Position position) {
        return new Pawn(position, "P", team, SCORE);
    }

    public static Pawn of(final Team team, final int column) {
        return new Pawn(team, getInitPosition(team, column));
    }

    public static List<Pawn> getInitPawns(final Team team) {
        List<Pawn> pawns = new ArrayList<>();
        ColumnConverter.getPawnInitCols().forEach((column) -> pawns.add(Pawn.of(team, column)));
        return pawns;
    }

    private static Position getInitPosition(final Team team, final int column) {
        if (team.equals(Team.BLACK)) {
            return new Position(RowConverter.getLocation(BLACK_TEAM_ROW), column);
        }
        return new Position(RowConverter.getLocation(WHITE_TEAM_ROW), column);
    }

    @Override
    public List<Position> getMovablePositions(final Board board) {
        List<Position> movablePositions = new ArrayList<>();
        addAttackablePositions(movablePositions, board);
        addMovablePositions(movablePositions, board, SINGLE_MOVE_DEGREE);

        if (getPosition().isInitPositionByTeam(getTeam()) && canDoubleMove(board)) {
            addMovablePositions(movablePositions, board, DOUBLE_MOVE_DEGREE);
        }
        return movablePositions;
    }

    private void addMovablePositions(final List<Position> movablePositions, final Board board, final int degree) {
        Position curPosition = getPosition();
        if (!board.validateRange(curPosition.getRow() + getStraightRow(degree), curPosition.getColumn())) {
            return;
        }
        Position movedPosition = new Position(curPosition.getRow() + getStraightRow(degree), curPosition.getColumn());

        if (!board.piecesByTeam(Team.BLACK).containByPosition(movedPosition) && !board.piecesByTeam(Team.WHITE).containByPosition(movedPosition)) {
            movablePositions.add(movedPosition);
        }
    }

    private boolean canDoubleMove(final Board board) {
        Position curPosition = getPosition();
        Position oneMovedPosition = new Position(curPosition.getRow() + getStraightRow(1), curPosition.getColumn());
        return !board.piecesByTeam(Team.BLACK).containByPosition(oneMovedPosition) && !board.piecesByTeam(Team.WHITE).containByPosition(oneMovedPosition);
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
        int[] columnDir = {-1, 1};
        checkAttackPosition(movablePositions, board, rowDir, columnDir);
    }

    private void addWhiteTeamAttackPosition(final List<Position> movablePositions, final Board board) {
        int[] rowDir = {-1, -1};
        int[] columnDir = {-1, 1};
        checkAttackPosition(movablePositions, board, rowDir, columnDir);
    }

    private void checkAttackPosition(final List<Position> movablePositions, final Board board, final int[] rowDir, final int[] columnDir) {
        Position curPosition = getPosition();
        for (int dir = 0; dir < rowDir.length; ++dir) {
            int nextRow = curPosition.getRow() + rowDir[dir];
            int nextCol = curPosition.getColumn() + columnDir[dir];
            addAttackablePosition(movablePositions, board, nextRow, nextCol);
        }
    }

    private void addAttackablePosition(final List<Position> movablePositions, Board board, final int nextRow, final int nextColumn) {
        if (!board.validateRange(nextRow, nextColumn)) {
            return;
        }

        Position attackPosition = new Position(nextRow, nextColumn);
        Pieces otherTeamPieces = board.piecesByTeam(Team.getAnotherTeam(getTeam()));

        if (otherTeamPieces.containByPosition(attackPosition)) {
            movablePositions.add(attackPosition);
        }
    }

    @Override
    public boolean isMoveAble(final List<Position> movablePositions, final Board board, final int nextRow, final int nextColumn) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
