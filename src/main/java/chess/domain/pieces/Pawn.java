package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private static final String BLACK_TEAM_ROW = "7";
    private static final String WHITE_TEAM_ROW = "2";

    public Pawn(final Team team, final Position position) {
        super(position, "P", team);
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
    public List<Position> getMovablePositions(final Board board) {
        List<Position> movablePositions = new ArrayList<>();
        addAttackablePositions(movablePositions, board);
        addMovablePositions(movablePositions, board, 1);

        if (getTeam() == Team.BLACK) {
            blackInitMove(board, movablePositions);
        }
        if (getTeam() == Team.WHITE) {
            whiteInitMove(board, movablePositions);
        }
        return movablePositions;
    }

    private void whiteInitMove(final Board board, final List<Position> movablePositions) {
        if (getPosition().getRow() == Row.getLocation(WHITE_TEAM_ROW)) {
            addMovablePositions(movablePositions, board, 2);
        }
    }

    private void blackInitMove(final Board board, final List<Position> movablePositions) {
        if (getPosition().getRow() == Row.getLocation(BLACK_TEAM_ROW)) {
            addMovablePositions(movablePositions, board, 2);
        }
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
        int[] dx = {1, 1};
        int[] dy = {-1, 1};
        checkAttackPosition(movablePositions, board, dx, dy);
    }

    private void addWhiteTeamAttackPosition(final List<Position> movablePositions, final Board board) {
        int[] dx = {-1, -1};
        int[] dy = {-1, 1};
        checkAttackPosition(movablePositions, board, dx, dy);
    }

    private void checkAttackPosition(final List<Position> movablePositions, final Board board, final int[] dx, final int[] dy) {
        Position curPosition = getPosition();
        for (int dir = 0; dir < dx.length; ++dir) {
            int nx = curPosition.getRow() + dx[dir];
            int ny = curPosition.getCol() + dy[dir];
            addAttackablePosition(movablePositions, board, nx, ny);
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
