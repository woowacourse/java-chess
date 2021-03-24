package chess.domain.move;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Pieces;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class PawnMove implements Movable {
    @Override
    public List<Position> allMovablePosition(final Piece piece, final Board board, final int[] rowDir, final int[] colDir) {
        List<Position> movablePositions = new ArrayList<>();
        Position curPosition = piece.getPosition();
        for (int dir = 0; dir < rowDir.length; ++dir) {
            Position nextPosition = curPosition.next(rowDir[dir], colDir[dir]);
            addAttackablePosition(movablePositions, board, nextPosition, piece.getTeam());
        }

        return movablePositions;
    }

    private void addAttackablePosition(final List<Position> movablePositions, final Board board, final Position position, final Team team) {
        if (!board.validateRange(position)) {
            return;
        }

        Pieces otherTeamPieces = board.piecesByTeam(Team.enemyTeam(team));

        if (otherTeamPieces.containByPosition(position)) {
            movablePositions.add(position);
        }
    }
}
