package chess.domain.move;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class MultiMoving implements Moving {

    @Override
    public List<Position> movablePositions(final Piece piece, final Board board, final int[] rowDirections, final int[] colDirections) {
        List<Position> movablePositions = new ArrayList<>();
        for (int direction = 0; direction < rowDirections.length; ++direction) {
            addMovablePositions(piece, board, movablePositions, rowDirections[direction], colDirections[direction]);
        }

        return movablePositions;
    }

    private void addMovablePositions(final Piece piece, final Board board, final List<Position> movablePositions, final int rowDirection, final int colDirection) {
        int distance = 1;
        Position currentPosition = piece.position();
        Position nextPosition = currentPosition.next(rowDirection * distance, colDirection * distance);
        Team team = piece.team();

        while (canMove(board, movablePositions, nextPosition, team)) {
            distance++;
            nextPosition = currentPosition.next(rowDirection * distance, colDirection * distance);
        }
    }

    private boolean canMove(final Board board, final List<Position> movablePositions, final Position nextPosition, final Team team) {
        if (!board.validatesPieceWithinBoardRange(nextPosition)) {
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
