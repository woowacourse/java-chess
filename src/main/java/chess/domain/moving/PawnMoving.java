package chess.domain.moving;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class PawnMoving implements Moving {
    private final int[] whiteRowDirection = {-1, -1};
    private final int[] whiteColDirection = {-1, 1};
    private final int[] blackRowDirection = {1, 1};
    private final int[] blackColDirection = {-1, 1};
    private List<Position> movablePositions = new ArrayList<>();

    @Override
    public List<Position> allMovablePositions(final Piece piece, final Board board) {
        movablePositions = new ArrayList<>();
        addAttackablePositions(piece, board);
        int positionsSize = movablePositions.size();
        addStraightPosition(board, piece, 1);
        Position currentPosition = piece.position();
        Team team = board.teamByPiece(piece);
        if (currentPosition.isSameInitPawnPositionByTeam(team) && positionsSize < movablePositions.size()) {
            addStraightPosition(board, piece, 2);
        }
        return movablePositions;
    }

    private void addAttackablePositions(final Piece piece, final Board board) {
        Team team = board.teamByPiece(piece);
        if (Team.WHITE.equals(team)) {
            movablePositions(piece, board, whiteRowDirection, whiteColDirection);
            return;
        }
        movablePositions(piece, board, blackRowDirection, blackColDirection);
    }

    private void movablePositions(final Piece piece, final Board board, final int[] rowDirections, final int[] colDirections) {
        Position curPosition = piece.position();
        Team team = board.teamByPiece(piece);
        for (int direction = 0; direction < rowDirections.length; ++direction) {
            Position nextPosition = curPosition.next(rowDirections[direction], colDirections[direction]);
            addAttackablePosition(board, nextPosition, team);
        }
    }

    private void addAttackablePosition(final Board board, final Position position, final Team team) {
        if (!board.isWithinBoardRange(position)) {
            return;
        }
        if (board.existsPieceByTeam(Team.enemyTeam(team), position)) {
            movablePositions.add(position);
        }
    }

    private void addStraightPosition(final Board board, final Piece piece, final int degree) {
        Position currentPosition = piece.position();
        Team team = board.teamByPiece(piece);
        Position nextPosition = currentPosition.next(straightRow(team, degree), 0);
        if (!board.isWithinBoardRange(nextPosition)) {
            return;
        }

        if (!board.piecesByTeam(Team.BLACK).containsPosition(nextPosition) && !board.piecesByTeam(Team.WHITE).containsPosition(nextPosition)) {
            movablePositions.add(nextPosition);
        }
    }

    private int straightRow(final Team team, final int degree) {
        if (team.equals(Team.BLACK)) {
            return degree;
        }
        return -degree;
    }
}
