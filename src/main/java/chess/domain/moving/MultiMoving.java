package chess.domain.moving;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiMoving implements Moving {
    private List<Position> movablePositions = new ArrayList<>();

    public List<Position> movablePositions(final Piece piece, final Board board, final int[] rowDirection, final int[] colDirection) {
        movablePositions = new ArrayList<>();
        for (int direction = 0; direction < rowDirection.length; ++direction) {
            addMovablePositions(piece, board, rowDirection[direction], colDirection[direction]);
        }
        return movablePositions;
    }

    private void addMovablePositions(final Piece piece, final Board board, final int rowDirection, final int colDirection) {
        int distance = 1;
        Position currentPosition = piece.position();
        Position nextPosition = currentPosition.next(rowDirection * distance, colDirection * distance);
        Team team = board.teamByPiece(piece);

        while (canMove(board, nextPosition, team)) {
            distance++;
            nextPosition = currentPosition.next(rowDirection * distance, colDirection * distance);
        }
    }

    private boolean canMove(final Board board, final Position nextPosition, final Team team) {
        if (!board.isWithinBoardRange(nextPosition)) {
            return false;
        }
        if (board.existsPieceByTeam(team, nextPosition)) {
            return false;
        }
        if (board.existsPieceByTeam(Team.enemyTeam(team), nextPosition)) {
            movablePositions.add(nextPosition);
            return false;
        }
        movablePositions.add(nextPosition);
        return true;
    }
}
