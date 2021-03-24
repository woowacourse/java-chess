package chess.domain.move;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class SingleMove implements Movable {
    @Override
    public List<Position> allMovablePosition(final Piece piece, final Board board, final int[] rowDir, final int[] colDir) {
        List<Position> movablePositions = new ArrayList<>();
        Position currentPosition = piece.getPosition();
        Team team = piece.getTeam();

        for (int i = 0; i < rowDir.length; i++) {
            Position nextPosition = currentPosition.next(rowDir[i], colDir[i]);
            addMovablePositions(board, movablePositions, nextPosition, team);
        }
        return movablePositions;
    }

    private void addMovablePositions(final Board board, final List<Position> movablePositions, final Position nextPosition, final Team team) {
        if (!board.validateRange(nextPosition)) {
            return;
        }
        if (board.piecesByTeam(team).containByPosition(nextPosition)) {
            return;
        }
        movablePositions.add(nextPosition);
    }
}
