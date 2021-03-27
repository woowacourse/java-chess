package chess.domain.pieces.Movable;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.List;

public interface MultiMove {
    default boolean isMoveAbleDir(final List<Position> movablePositions, final Board board, final int nextRow, final int nextCol, final Team team) {
        if (!board.validateRange(nextRow, nextCol)) {
            return false;
        }
        if (board.piecesByTeam(team).containByPosition(new Position(nextRow, nextCol))) {
            return false;
        }
        if (board.piecesByTeam(Team.getAnotherTeam(team)).containByPosition(new Position(nextRow, nextCol))) {
            movablePositions.add(new Position(nextRow, nextCol));
            return false;
        }
        return true;
    }
}
