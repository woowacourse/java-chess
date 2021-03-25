package chess.domain.move;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Pieces;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class PawnMoving implements Moving {

    @Override
    public List<Position> movablePositions(final Piece piece, final Board board, final int[] rowDirections, final int[] colDirections) {
        List<Position> movablePositions = new ArrayList<>();
        Position curPosition = piece.position();
        for (int direction = 0; direction < rowDirections.length; ++direction) {
            Position nextPosition = curPosition.next(rowDirections[direction], colDirections[direction]);
            addAttackablePosition(movablePositions, board, nextPosition, piece.team());
        }

        return movablePositions;
    }

    private void addAttackablePosition(final List<Position> movablePositions, final Board board, final Position position, final Team team) {
        if (!board.validatesRange(position)) {
            return;
        }

        Pieces enemyPieces = board.piecesByTeam(Team.enemyTeam(team));

        if (enemyPieces.containsPosition(position)) {
            movablePositions.add(position);
        }
    }
}
