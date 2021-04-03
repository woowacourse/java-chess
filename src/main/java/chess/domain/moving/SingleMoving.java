package chess.domain.moving;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class SingleMoving implements Moving {
    private List<Position> movablePositions = new ArrayList<>();

    public List<Position> movablePositions(final Piece piece, final Board board, final int[] rowDirection, final int[] colDirection) {
        movablePositions = new ArrayList<>();
        Position currentPosition = piece.position();
        Team team = board.teamByPiece(piece);

        for (int direction = 0; direction < rowDirection.length; direction++) {
            Position nextPosition = currentPosition.next(rowDirection[direction], colDirection[direction]);
            addMovablePositions(board, nextPosition, team);
        }
        return movablePositions;
    }

    private void addMovablePositions(final Board board, final Position nextPosition, final Team team) {
        if (!board.isWithinBoardRange(nextPosition)) {
            return;
        }
        if (board.existsPieceByTeam(team, nextPosition)) {
            return;
        }
        movablePositions.add(nextPosition);
    }
}
