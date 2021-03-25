package chess.domain.move;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Pieces;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class SingleMoving implements Moving {

    @Override
    public List<Position> movablePositions(final Piece piece, final Board board, final int[] rowDirections, final int[] colDirections) {
        List<Position> movablePositions = new ArrayList<>();
        Position currentPosition = piece.position();
        Team team = piece.team();

        for (int i = 0; i < rowDirections.length; i++) {
            Position nextPosition = currentPosition.next(rowDirections[i], colDirections[i]);
            addMovablePositions(board, movablePositions, nextPosition, team);
        }
        return movablePositions;
    }

    private void addMovablePositions(final Board board, final List<Position> movablePositions, final Position nextPosition, final Team team) {
        if (!board.validatesRange(nextPosition)) {
            return;
        }
        Pieces pieces = board.piecesByTeam(team);
        if (pieces.containsPosition(nextPosition)) {
            return;
        }
        movablePositions.add(nextPosition);
    }
}
